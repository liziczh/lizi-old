# !/bin/bash

JAVA_HOME="/opt/jdk"
APP_HOME="/opt/apps"
APP_MAIN=appInfo-crawler.jar

pid=0

getPID(){
   javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAIN`
   if [ -n "$javaps" ]; then
      pid=`echo $javaps | awk '{print $1}'`
   else
      pid=0
   fi
}

getCrawlerStatus(){
    crawler_status=`curl -s localhost:8080/api/crawler/status`
    echo "Crawler Status: ${crawler_status}"
}

getServerStatus(){
   getPID
   echo "==============================================================================================="
   if [ $pid -ne 0 ]; then
      echo "$APP_MAIN is running(PID=$pid)"
      getCrawlerStatus
      echo "==============================================================================================="
   else
      echo "$APP_MAIN is not running"
      echo "==============================================================================================="
   fi
}

getServerStatus

