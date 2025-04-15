#!/bin/sh

ORDER='order'
PAYMENT='payment'
ARTICLE-UI='article-ui'

if [ "$#" -eq 0 ];
then
  docker-compose -f tools.yml -f apps.yml -f kogito-tools.yml up -d --force-recreate $ORDER
else
  docker-compose -f tools.yml -f apps.yml -f kogito-tools.yml up -d --force-recreate $1
fi