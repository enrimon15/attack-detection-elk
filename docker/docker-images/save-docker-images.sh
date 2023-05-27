#!/bin/bash

docker save opensearchproject/opensearch -o opensearch.tar opensearchproject/opensearch:1.2.0
docker save opensearchproject/logstash-oss-with-opensearch-output-plugin -o logstash.tar opensearchproject/logstash-oss-with-opensearch-output-plugin:7.16.2
docker save opensearchproject/opensearch-dashboards -o opensearch-dashboard.tar opensearchproject/opensearch-dashboards:1.2.0
docker save bitnami/kafka -o kafka.tar bitnami/kafka:2.6.0
docker save bitnami/zookeeper -o zookeeper.tar bitnami/zookeeper:3.8.0
docker save provectuslabs/kafka-ui -o kafka-ui.tar provectuslabs/kafka-ui:latest
docker save postgres -o postgres.tar postgres:latest
docker save metasploitframework/metasploit-framework -o metasploit.tar metasploitframework/metasploit-framework:latest
docker save docker-vulnerable-mts -o vulnerable-mts.tar docker-vulnerable-mts:latest
