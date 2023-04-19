#!/bin/bash
docker-compose up -d
sleep 20

# entro nel container vulnerabile ubuntu
#docker exec -it vulnerable-mts bash

# avvio packetbeat nel container vulnerabile ubuntu
docker exec vulnerable-mts service packetbeat start -d -e --strict.perms=false
docker exec vulnerable-mts service auditbeat start -d -e --strict.perms=false
# to check if beat is running you can execute: service packetbeat status or service auditbeat status
sleep 4

# import dashboards
curl -X POST "http://localhost:5601/api/saved_objects/_import?overwrite=true" -H "osd-xsrf: true" -k --form file=@config/opensearch-dashboards/dashboards.ndjson