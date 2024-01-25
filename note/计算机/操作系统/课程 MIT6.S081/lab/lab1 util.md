https://pdos.csail.mit.edu/6.S081/2021/labs/util.html



代码

https://github.com/PKUFlyingPig/MIT6.S081-2020fall

https://blog.csdn.net/weixin_48283247/article/details/120602005





sleep.c

```javascript
#include "kernel/types.h"
#include "kernel/stat.h"
#include "user/user.h"

int 
main(int argc, char const *argv[])
{
	if (argc != 2){
		fprintf(2, "please enter a integer as parameter \n");
		exit(1);
	}
	int time = atoi(argv[1]);
	sleep(time);
	
	exit(0);
}

```

pingpong.c

```javascript
#include "kernel/types.h"
#include "kernel/stat.h"
#include "user/user.h"

int main(int argc, char const *argv[])
{
	int p[2];
	pipe(p);
	char buf[10];
	if(fork()==0){//子进程
		read(p[0],buf,sizeof(buf));
		fprintf(1,"%d: received %s\n",getpid(),buf);
		close(p[0]);
		char * pong = "pong";
		write(p[1],pong,sizeof(pong));
		close(p[1]);
	}else{//父进程
		char * ping = "ping";
		write(p[1],ping,sizeof(ping));
		close(p[1]);
		wait(0);
		read(p[0],buf,sizeof(buf));
		fprintf(1,"%d: received %s\n",getpid(),buf);
		close(p[0]);
	}
	exit(0);
}
```

primes.c

```javascript
#include "kernel/types.h"
#include "kernel/stat.h"
#include "user/user.h"

void primes(int p[2]){
	int first;
	int num;
	close(p[1]);
	if(read(p[0],&first,sizeof(int))){
		fprintf(1,"prime %d\n",first);
		int newP[2];
		pipe(newP);
		if(fork()==0){
			close(newP[0]);
			while(read(p[0],&num,sizeof(int))){
				if(num % first != 0){
					write(newP[1],&num,sizeof(int));
				}
			}
			close(p[0]);
			close(newP[1]);
		}else{
			wait(0);
			primes(newP);
		}
	}
	exit(0);
}

int main(int argc, char *argv[]){
	int p[2];
	pipe(p);
	if(fork()==0){
		close(p[0]);
		for(int i=2;i<=35;i++){
			write(p[1],&i,sizeof(int));
		}
		close(p[1]);
		exit(0);
	}else{
		wait(0);
		primes(p);
		exit(0);
	}
	
}
```

find.c

```javascript
#include "kernel/types.h"
#include "kernel/stat.h"
#include "user/user.h"
#include "kernel/fs.h"

void find(char *path,char *fileName){
	char buf[512], *p;
	int fd;
	struct dirent de;
	struct stat st;

	if((fd = open(path, 0)) < 0){
		fprintf(2, "find: cannot open %s\n", path);
		return;
	}
	
	if(fstat(fd, &st) < 0){
		fprintf(2, "find: cannot stat %s\n", path);
		close(fd);
		return;
	}
	
	if(st.type!=T_DIR){
		fprintf(2, "find: %s is not a directory \n", path);
		close(fd);
		return;
	}
	
	if(strlen(path) + 1 + DIRSIZ + 1 > sizeof buf){
		printf("find: path too long \n");
		return;
	}
	strcpy(buf, path);
	p = buf+strlen(buf);
	*p++ = '/';//p指向buf中path结束位置
	while(read(fd, &de, sizeof(de)) == sizeof(de)){
		if (de.inum == 0 || strcmp(de.name, ".") == 0 || strcmp(de.name, "..") == 0) continue;
		memmove(p, de.name, DIRSIZ);//p指向buf后半段，文件名
		p[DIRSIZ] = 0;
		
		if(stat(buf, &st) < 0){
			fprintf(2, "find: cannot stat %s\n", path);
			continue;
		}
		
		if(st.type==T_DIR){
			find(buf,fileName); 
		}else if(strcmp(p,fileName)==0){
			fprintf(1,"%s\n",buf);
		}
	}
	
	close(fd);
}

int main(int argc, char *argv[])
{
	if(argc < 3){
	fprintf(1,"usage: find path filename");
		exit(0);
	}
	find(argv[1],argv[2]);
	exit(0);
}
```

xargs.c

```javascript
#include "kernel/types.h"
#include "kernel/stat.h"
#include "user/user.h"
#include "kernel/param.h"

int main(int argc, char *argv[]){
	if (argc < 2) {
        fprintf(2, "usage: xargs command\n");
        exit(1);
    }
	char *newArgvs[MAXARG];
	for (int i = 1; i < argc; ++i) {
        newArgvs[i-1] = argv[i];
    }
	char buf[1024] = {0};
	int n = 0;
	while(read(0,buf+n,1)){
		if (buf[n] == '\n'){
			buf[n] = 0;
			if (fork() == 0) {
				newArgvs[argc-1] = buf;
				//fprintf(1,"command:%s,param0:%s,param1:%s,param2:%s[done]\n",argv[1], newArgvs[0], newArgvs[1], newArgvs[2]); 
				exec(argv[1], newArgvs);
				exit(1);
			}
			n = 0;
			wait(0);
		}
		n++;
		if (n == 1024){
			fprintf(2, "argument is too long\n");
			exit(1);
		}
	}
	exit(0);
}
```

