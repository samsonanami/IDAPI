#!/bin/sh

mkdir -p /opt/wildfly/10.0.0/temp/zip

cp /tmp/ORION/dev/*.zip  /opt/wildfly/10.0.0/temp/zip/.
cd /opt/wildfly/10.0.0/temp/zip
unzip *.zip > /dev/null

/opt/wildfly/10.0.0/bin/jboss-cli.sh --connect --commands="ls deployment" > /opt/wildfly/10.0.0/temp/temp_running_war.file

echo "################## Files Running On Wildfly ######################"
cat /opt/wildfly/10.0.0/temp/temp_running_war.file

unzip -l /tmp/ORION/dev/*.zip | sed -n '4,$p' | head -n -2 |awk '{ print $4 }'| grep 'war' | cut -d'-' -f1 > /opt/wildfly/10.0.0/temp/temp_zipped_war.file

echo "################## Files To Be Deployed On Wildfly ######################" 
cat /opt/wildfly/10.0.0/temp/temp_zipped_war.file

lines=$(/opt/wildfly/10.0.0/bin/jboss-cli.sh --connect --commands="ls deployment" | wc -l)

while IFS='' read -r lines1 || [[ -n "$lines1" ]]; do
	while IFS='' read -r lines2 || [[ -n "$lines2" ]]; do
		echo "$lines1" | grep "$lines2" >>  /opt/wildfly/10.0.0/temp/temp_undeploy.file
	done < /opt/wildfly/10.0.0/temp/temp_zipped_war.file
done < /opt/wildfly/10.0.0/temp/temp_running_war.file

while IFS='' read -r lines3 || [[ -n "$lines3" ]]; do
	/opt/wildfly/10.0.0/bin/jboss-cli.sh --connect --command="undeploy '$lines3'"
done < /opt/wildfly/10.0.0/temp/temp_undeploy.file

unzip -l /tmp/ORION/dev/*.zip | sed -n '4,$p' | head -n -2 |awk '{ print $4 }'| grep 'war'> /opt/wildfly/10.0.0/temp/temp_zipped_war.file

while IFS='' read -r lines4 || [[ -n "$lines4" ]]; do
	/opt/wildfly/10.0.0/bin/jboss-cli.sh --connect --command="deploy --force /opt/wildXXXfly/10.0.0/temp/zip/$lines4"
	echo $?
done < /opt/wildfly/10.0.0/temp/temp_zipped_war.file

rm -rf  /opt/wildfly/10.0.0/temp/




