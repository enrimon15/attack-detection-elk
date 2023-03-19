#!/bin/bash
docker-compose -f docker-compose-tomcat-pb.yml up -d
sleep 5
docker exec -it vulnerable-mts bash
service packetbeat start -d -e --strict.perms=false
exit