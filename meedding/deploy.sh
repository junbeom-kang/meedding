#!/bin/bash

REPOSITORY=/home/ec2-user/app/step1
PROJECT_NAME=meedding/meedding

cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull"

git pull

echo "> 프로젝트 Build 시작"

./gradlew build

echo "> Build 파일 복사"

cp $REPOSITORY/meedding/meedding/build/libs/*.jar $REPOSITORY/meedding

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f meedding)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -2 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls  /home/ec2-user/app/step1/meedding |grep jar| tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar $REPOSITORY/meedding/$JAR_NAME &
