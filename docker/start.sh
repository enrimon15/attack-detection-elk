#!/bin/bash
docker-compose up -d
sleep 20
# entro nel container vulnerabile ubuntu
docker exec -it vulnerable-mts bash
# avvio packetbeat
service packetbeat start -d -e --strict.perms=false
service auditbeat start -d -e --strict.perms=false
# to check if beat is running you can execute: service packetbeat status or service auditbeat status
sleep 3
# esco dal container e ritorno alla mia shell
exit