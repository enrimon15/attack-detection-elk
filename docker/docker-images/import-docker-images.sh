#!/bin/bash

docker load --input elasticsearch.tar
docker load --input logstash.tar
docker load --input kibana.tar
docker load --input kafka.tar
docker load --input zookeeper.tar
docker load --input kafka-ui.tar
docker load --input postgres.tar
docker load --input metasploit.tar
docker load --input vulnerable-mts.tar