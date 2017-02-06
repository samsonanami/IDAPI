#!/bin/sh

echo $#
echo $1 $2 $3

if [ "$1" == "true" ]
then
	echo "S1('$S1') is not equal to S2('$S2')"
fi