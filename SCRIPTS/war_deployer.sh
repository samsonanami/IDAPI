#!/bin/sh

WILDFLY_LOC=/opt/wildfly/10.0.0
COPY_LOC=/tmp/ORION

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
