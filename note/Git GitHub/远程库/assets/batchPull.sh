#/bin/bash

for gitPath in $(find /y/project/ -type d -name ".git" -print)
	do
		projectPath=${gitPath%%.git}
		echo -e "\n正在拉取"$projectPath
		cd $projectPath
		git pull
	done
	