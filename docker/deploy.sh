#!/bin/sh

# 使用说明，用来提示输入参数
usage() {
	echo "Usage: sh 执行脚本.sh [port|base|start|stop|rm]"
	exit 1
}

# 开启所需端口
port(){
	firewall-cmd --add-port=1024/tcp --permanent
	firewall-cmd --add-port=3000/tcp --permanent
	firewall-cmd --add-port=3306/tcp --permanent
	firewall-cmd --add-port=6379/tcp --permanent
	firewall-cmd --add-port=8000/tcp --permanent
	firewall-cmd --add-port=8012/tcp --permanent
	firewall-cmd --add-port=8080/tcp --permanent
	service firewalld restart
}

# 启动基础环境（必须）
base(){
	docker-compose up -d ry-mysql ry-redis
}

start(){
	docker-compose up -d ry-server ry-ui
}

# 关闭所有环境/模块
stop(){
	docker-compose stop
}

# 删除所有环境/模块
rm(){
	docker-compose rm
}

# 根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"port")
	port
;;
"base")
	base
;;
"start")
	start
;;
"stop")
	stop
;;
"rm")
	rm
;;
*)
	usage
;;
esac
