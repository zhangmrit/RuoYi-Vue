#!/bin/sh

cd ../
git pull
mvn clean package -DskipTests

cd ruoyi-ui
npm install
npm run build

cd ../docker
sh copy.sh
docker-compose up -d -V --build --no-deps ry-server ry-ui