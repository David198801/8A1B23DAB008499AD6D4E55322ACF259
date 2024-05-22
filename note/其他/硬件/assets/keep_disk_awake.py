import os
import time

# 获取当前脚本所在目录
current_dir = os.path.dirname(os.path.realpath(__file__))
print(current_dir)

# 指定写入文件的路径
file_path = os.path.join(current_dir, 'output.txt')

# 间隔时间（秒）
interval = 10

file = open(file_path, 'w')

i = 1

while True:
    
    # 写入时间戳到文件
    file.write(str(i)+'\n')
    file.flush()
    print(i)
    i+=1

    # 每隔指定时间执行一次
    time.sleep(interval)
