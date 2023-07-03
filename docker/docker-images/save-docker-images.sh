#!/bin/bash

docker save docker.elastic.co/elasticsearch/elasticsearch -o elasticsearch.tar docker.elastic.co/elasticsearch/elasticsearch:7.14.0
docker save docker.elastic.co/logstash/logstash -o logstash.tar docker.elastic.co/logstash/logstash:7.14.0
docker save docker.elastic.co/kibana/kibana -o kibana.tar docker.elastic.co/kibana/kibana:7.14.0
docker save bitnami/kafka -o kafka.tar bitnami/kafka:2.6.0
docker save bitnami/zookeeper -o zookeeper.tar bitnami/zookeeper:3.8.0
docker save provectuslabs/kafka-ui -o kafka-ui.tar provectuslabs/kafka-ui:latest
docker save postgres -o postgres.tar postgres:latest
docker save metasploitframework/metasploit-framework -o metasploit.tar metasploitframework/metasploit-framework:latest
docker save docker-vulnerable-mts -o vulnerable-mts.tar docker-vulnerable-mts:latest
