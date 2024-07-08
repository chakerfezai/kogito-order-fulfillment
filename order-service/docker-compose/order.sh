#!/bin/sh

if [ $1 = 'stop' ]; then
    echo "stop all orders configs";
    docker-compose -f tools.yml -f apps.yml -f kogito-tools.yml down
elif [ $1 = 'run' ];   then
    echo "run all orders configs"
    docker-compose -f tools.yml -f apps.yml -f kogito-tools.yml up -d
else
    echo "choose one of this tow options [stop,run]"
fi
