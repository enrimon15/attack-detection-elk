#!/bin/bash
docker-compose up -d
sleep 20
# entro nel container vulnerabile ubuntu
docker exec -it vulnerable-mts bash
# avvio packetbeat
service packetbeat start -d -e --strict.perms=false
sleep 3
# esco dal container e ritorno alla mia shell
exit