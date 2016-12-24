#! /bin/sh

NAME="Hermese" DESC="Hermese messaging agent "

EXEC="/usr/bin/jsvc"

FILE_PATH="/opt/orion/hermese"

JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-amd64"

CLASS_PATH="/$FILE_PATH/HermeseAgent.jar:/$FILE_PATH/lib/commons-daemon-1.0.15.jar:/$FILE_PATH/lib/*:/$FILE_PATH/config/*:."

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
    $EXEC -home $JAVA_HOME -cp $CLASS_PATH -user $USER $JAVA_OPT -outfile $LOG_OUT -errfile $LOG_ERR -pidfile $PID $1 $CLASS
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
    *)
        echo "Usage: {start|stop}" >&2
    ;;
esac