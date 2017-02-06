#!/bin/sh

#Wildfly hosted location
WILDFLY_LOC=/opt/wildfly/10.0.0

#Wildfly build copy locastion
COPY_LOC=/tmp/ORION

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

if [ "$1" == "true" ] || [ "$2" == "true" ]
then
    echo "Deploying War Engine"; war_deploy
fi