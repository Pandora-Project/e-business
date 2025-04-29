#!/bin/sh
# Usage: wait-for.sh host:port [-t timeout] [-- command args]
# From https://github.com/Eficode/wait-for

HOST=$(echo $1 | cut -d : -f 1)
PORT=$(echo $1 | cut -d : -f 2)
TIMEOUT=15

while getopts ":t:" OPT; do
  case $OPT in
    t) TIMEOUT=$OPTARG ;;
  esac
done

shift $((OPTIND - 1))

echo "Waiting $TIMEOUT seconds for $HOST:$PORT..."

for i in `seq $TIMEOUT` ; do
  nc -z $HOST $PORT > /dev/null 2>&1
  result=$?
  if [ $result -eq 0 ] ; then
    if [ $# -gt 0 ] ; then
      exec "$@"
    fi
    exit 0
  fi
  sleep 1
done

echo "Timeout reached after $TIMEOUT seconds" >&2
exit 1