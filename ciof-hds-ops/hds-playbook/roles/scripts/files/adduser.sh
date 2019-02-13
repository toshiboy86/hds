#!/bin/sh

#
# adduser.sh
# (C) 2019. Industrial Value Chain Initiative
#

cd `dirname $0`

usage() {
	echo "Usage: $myname <username> <password> <role>"
}

username=$1
password=$2
role=$3

if [ -z "$username" -o -z "$password" -o -z "$role" ]; then
	usage
	exit 1
fi

if [ "$role" != "ADMINISTRATOR" -a "$role" != "USER" ]; then
	usage
	echo "Note: role should be ADMINISTRATOR or USER"
	exit 1
fi

hashed_password=`./hashpassword.sh $password`
echo $hashed_password

psql -h localhost -U postgres ciofuserdb -c "insert into users(username, password) values('$username', '$hashed_password')"
psql -h localhost -U postgres ciofuserdb -c "insert into user_roles(user_id, role_id) select user_id,role_id from users,roles where username='$username' and role_name='$role';"