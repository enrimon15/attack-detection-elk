#!/bin/bash
docker-compose up -d
sleep 60
# avvio packetbeat
docker exec vulnerable-mts service packetbeat start -d -e --strict.perms=false
docker exec vulnerable-mts service auditbeat start -d -e --strict.perms=false
# to check if beat is running you can execute: docker exec vulnerable-mts service packetbeat status or service auditbeat status

sleep 10

# import dashboards
curl -X POST "http://localhost:5601/api/saved_objects/_import?overwrite=true" -H "kbn-xsrf: true" -k --form file=@config/kibana-dashboards/dashboards.ndjson