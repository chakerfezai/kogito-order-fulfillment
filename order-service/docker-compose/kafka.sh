#!/bin/sh

if [ $1 = 'stop' ]; then
    echo "stop kafka cluster";
    docker-compose -f tools.yml down
elif [ $1 = 'run' ];   then
    echo "start kafka cluster"
    docker-compose -f tools.yml up -d
else
    echo "choose one of this tow options [stop,run]"
fi

#docker-compose -f tools.yml -f up -d