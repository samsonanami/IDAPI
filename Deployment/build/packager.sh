#!/bin/bash
tar cf payload.tar -C payload .
if [ -f "payload.tar" ];
	then
		gzip payload.tar
	if [ -f "payload.tar.gz" ];
	then
		cat decompress.sh payload.tar.gz > hermese.bsx
	else
		echo "payload.tar.gz does not exist"
		exit 1
	fi
else
	echo "payload.tar does not exist"
	exit 1
fi

echo "hermese.bsx created"
exit 0