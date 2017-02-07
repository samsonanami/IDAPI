#!/bin/sh

#Wildfly hosted location
WILDFLY_LOC=/opt/wildfly/10.0.0

#Wildfly build copy location
COPY_LOC=/tmp/ORION

HERMES_BACKUP=/opt/orion/hermese-backup

HERMESE_APP_LOC=/opt/orion

BUILD_NAME=hermese

#Date in which the script is run,used in backing up
DATE=`date +%F-%T`

# =================================================================== 
# DO NOT EDIT BELOW THIS LINE UNLESS    YOU KNOW WHAT YOU ARE DOING
# ===================================================================

war_deploy()
{
	rm -rf  $WILDFLY_LOC/temp/
	mkdir -p $WILDFLY_LOC/temp/zip

	cp $COPY_LOC/dev/*.zip  $WILDFLY_LOC/temp/zip/.
	cd $WILDFLY_LOC/temp/zip
	unzip *.zip > /dev/null

	$WILDFLY_LOC/bin/jboss-cli.sh --connect --commands="ls deployment" > $WILDFLY_LOC/temp/temp_running_war.file

	echo "################## Files Running On Wildfly ######################"
	cat $WILDFLY_LOC/temp/temp_running_war.file

	if [ "$1" == "true" ]
	then
		unzip -l $COPY_LOC/dev/*.zip | sed -n '4,$p' | head -n -2 |awk '{ print $4 }'| grep 'war' | grep 'OrionAPI' | cut -d'-' -f1 > $WILDFLY_LOC/temp/temp_zipped_war.file
	fi

	if [ "$2" == "true" ]
	then
		unzip -l $COPY_LOC/dev/*.zip | sed -n '4,$p' | head -n -2 |awk '{ print $4 }'| grep 'war' | grep 'OrionAuthAPI' | cut -d'-' -f1 >> $WILDFLY_LOC/temp/temp_zipped_war.file
	fi

	echo "################## Files To Be Deployed On Wildfly ######################" 
	cat $WILDFLY_LOC/temp/temp_zipped_war.file

	lines=$($WILDFLY_LOC/bin/jboss-cli.sh --connect --commands="ls deployment" | wc -l)

	while IFS='' read -r lines1 || [[ -n "$lines1" ]]; do
		while IFS='' read -r lines2 || [[ -n "$lines2" ]]; do
			echo "$lines1" | grep "$lines2" >>  $WILDFLY_LOC/temp/temp_undeploy.file
		done < $WILDFLY_LOC/temp/temp_zipped_war.file
	done < $WILDFLY_LOC/temp/temp_running_war.file

	while IFS='' read -r lines3 || [[ -n "$lines3" ]]; do
		$WILDFLY_LOC/bin/jboss-cli.sh --connect --command="undeploy '$lines3'"
	done < $WILDFLY_LOC/temp/temp_undeploy.file

	rm -rf $WILDFLY_LOC/temp/temp_zipped_war.file

	if [ "$1" == "true" ]
	then
		unzip -l $COPY_LOC/dev/*.zip | sed -n '4,$p' | head -n -2 |awk '{ print $4 }'| grep 'war' | grep 'OrionAPI' > $WILDFLY_LOC/temp/temp_zipped_war.file
	fi

	if [ "$2" == "true" ]
	then
		unzip -l $COPY_LOC/dev/*.zip | sed -n '4,$p' | head -n -2 |awk '{ print $4 }'| grep 'war' | grep 'OrionAuthAPI' >> $WILDFLY_LOC/temp/temp_zipped_war.file
	fi

	while IFS='' read -r lines4 || [[ -n "$lines4" ]]; do
		$WILDFLY_LOC/bin/jboss-cli.sh --connect --command="deploy --force $WILDFLY_LOC/temp/zip/$lines4"
		if [ $? -eq 0 ]
		then
			echo "Successfully Deployed $lines4"
		else
			echo "$lines4 Deployment Unsuccessfull" >&2
		fi
	done < $WILDFLY_LOC/temp/temp_zipped_war.file

	rm -rf  $WILDFLY_LOC/temp/
}

hermese()
{
	$HERMESE_APP_LOC/$BUILD_NAME/Hermese.sh $1
	ps aux | grep $HERMESE_APP_LOC/$BUILD_NAME
}

hermese_backup ()
{
	if [ -d $HERMES_BACKUP ]
	then
        	echo "Backup folder exists" > /dev/null
	else
        	mkdir -p $HERMES_BACKUP
	fi	

	if [ -d $HERMESE_APP_LOC/$BUILD_NAME ]
	then
		cd $HERMESE_APP_LOC
		tar -zcf $HERMES_BACKUP/$BUILD_NAME-$DATE.tgz $BUILD_NAME
		echo "Backup taken sucessfully"
	else
		echo "No Files to backup"
	fi
}

hermese_deletebackup()
{
	COUNT=`ls -1 $HERMES_BACKUP| wc -l`

	if [ $COUNT -lt 4 ]
	then    
	        echo "Less than 3 backups remaining"
	else    
	     	rm -rf `ls -dt $HERMES_BACKUP/* | awk 'NR>3'`
		echo "Deleted old backups sucessfully"
	fi
}

hermese_temp_backup()
{
    rm -rf $HERMESE_APP_LOC/temp
	mkdir -p $HERMESE_APP_LOC/temp
    cp $HERMESE_APP_LOC/$BUILD_NAME/config/spring-beans.xml $HERMESE_APP_LOC/temp/.
    cp $HERMESE_APP_LOC/$BUILD_NAME/config/spring-config.xml $HERMESE_APP_LOC/temp/.
    cp $HERMESE_APP_LOC/$BUILD_NAME/config/spring-datasource.xml $HERMESE_APP_LOC/temp/.
    cp $HERMESE_APP_LOC/$BUILD_NAME/config/applicationContext.xml $HERMESE_APP_LOC/temp/.
    cp $HERMESE_APP_LOC/$BUILD_NAME/Hermes.sh $HERMESE_APP_LOC/temp/.
}

app_deploy()
{
	yes | cp -rf $COPY_LOC/dev/*.zip  $HERMESE_APP_LOC/temp/.
    cd $HERMESE_APP_LOC/temp/
    rm -rf $HERMESE_APP_LOC/temp/*.war
    unzip *.zip
    rm -rf $HERMESE_APP_LOC/$BUILD_NAME
    $HERMESE_APP_LOC/temp/*.bsx
}

if [ "$1" == "true" ] || [ "$2" == "true" ]
then
    echo "Deploying War Engine"; war_deploy $1 $2
fi

if [ "$3" == "true" ]
then
    echo "Stopping java application"; hermese stop
    echo "Backing up config files"; hermese_temp_backup
    echo "Backing up java application"; hermese_backup
    echo "Deleting old backup files"; hermese_deletebackup
    echo "Starting java application"; hermese start
fi