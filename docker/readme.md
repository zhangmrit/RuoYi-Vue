

1. 安装docker和docker-compose 

2. 进入docker文件夹

3. 替换对应的文件
   
   需要更新时替换，不更新不用做任何事

4. 相关指令
   ```shell
      # 启动
      sudo sh deploy.sh start
      # 停止
      sudo sh deploy.sh down 
      # 移除容器
      sudo sh deploy.sh rm
      # 单独更新某个模块
      docker-compose up -d -V --no-deps --build [模块名] 
   
   ```








