#!/bin/sh

#
# base64basicauth.sh
# (C) 2019. Industrial Value Chain Initiative
#

password=$1
if [ -z "$password" ]; then
	echo "Usage: `basename $0` <username> <password>"
	exit 1
fi

echo -n $password | openssl dgst -sha256 -binary | openssl base64
