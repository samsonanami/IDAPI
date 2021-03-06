#! /bin/sh

NAME="Hermese" DESC="Hermese messaging agent "

EXEC="/usr/bin/jsvc"

FILE_PATH="/opt/orion/hermese"

JAVA_HOME="/usr/java/jdk1.8.0_101"

CLASS_PATH="/$FILE_PATH/HermeseAgent-0.1.0-SNAPSHOT.jar:/$FILE_PATH/lib/commons-daemon-1.0.15.jar:/$FILE_PATH/lib/*:/$FILE_PATH/config/*:."

CLASS="com.fintech.orion.hermes.service.Service"

ARGS=""

USER="root"

PID="/var/run/$NAME.pid"

DATE=$(date +"_%Y%m%d")

# System.out writes to this file...
LOG_OUT="/$FILE_PATH/$NAME$DATE.out"

# System.err writes to this file...
LOG_ERR="/$FILE_PATH/$NAME$DATE.err"

# TODO : JVM memory  allocation
JAVA_OPT="-Xms6g -Xmx6g -XX:MaxPermSize=256m"


jsvc_exec() {
    cd /$FILE_PATH
    $EXEC -home $JAVA_HOME -cp $CLASS_PATH -DapplicationContextFrom="file" -DcontextFilePath="/opt/orion/hermese/config" -user $USER $JAVA_OPT -outfile $LOG_OUT -errfile $LOG_ERR -pidfile $PID $1 $CLASS
}

case "$1" in
    start)
        echo "Starting hermese agent"
        jsvc_exec

        echo "This will take few seconds  ..."
	    sleep 15
	    echo  "Please use ps aux | grep $FILE_PATH to check the service state"
    ;;
    stop)
        echo "Stopping hermese  service"
        jsvc_exec "-stop"

        sleep 15
        echo "The $DESC has stopped."
	    echo  "Please use ps aux | grep $FILE_PATH  to ensure no instances running"
    ;;
    restart)
        echo "Attempting to restart $DESC"
        if [ -f "$PID" ]; then
            echo "Restarting $DESC"
            jsvc_exec "-stop"
            sleep 10
            jsvc_exec
            echo "This will take few seconds ..."
            sleep 15
            echo "Please use ps aux | grep $FILE_PATH to check the service status"
        else
            echo "No hermese agent was running. No cation taken."
        fi
    ;;
    *)
        echo "Usage: {start|stop|restart}" >&2
    ;;
esac