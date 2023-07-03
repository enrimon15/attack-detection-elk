# ELK-Apache Beat Data Pipeline - Network Monitoring and Attack Detection

Project to monitor a network and detect cyber attacks that can compromise the system, using sniffing tools and data processing pipeline. To implement the process are used tools for system monitoring (Apache Beat Stack) and data analysis tools (ELK Stack).
\
<br/>

Specifically (for testing purposes), a simulated cyber attack on a Tomcat server is performed using the **Metasploit framework**. The attack, as well as network activities and changes to system files, are detected through the network packet analyzer **Packetbeat** and the file-monitor **Auditbeat**, which monitor network traffic and system file integrity and regularly send the results to the message broker **Kafka**. These monitoring messages are then collected and consumed by **Logstash**, then transformed, and then are sent to the data search and analysis engine **Elasticsearch**. Using predefined rules and queries on Elasticsearch, network data and file-integrity data are analyzed, and possible anomalous activities are detected. Finally, through the visualization tool **Kibana**, it is possible to filter this data and display it on a dashboard with various alert charts that inform us of possible ongoing attacks.
\
There is also a **Java** application that, when launched, consumes the messages from Kafka (network data and file integrity data) and stores them to a **MongoDB** database for additional data persistence.

---

## Architecture

The entire architecture is built using a docker-compose file. The services involved are:

- ***Metasploit***: to simulate a cyber attack on a vulnerable container.
- ***Ubuntu*** container with a vulnerable ***Tomcat*** server: containging Packetbeat and Auditbeat which monitor network and system files.
    - ***Packetbeat***: monitors network data of various protocols, in our case HTTP, and sends it to the Kafka message broker.
    - ***Auditbeat***: monitors system file integrity and sends the data to the Kafka message broker.
- ***Kafka***: receives data from Packetbeat and Auditbeat sources.
- ***Kafka UI***: a user interface for Kafka (useful for testing and debugging).
- ***Logstash***: consumes messages from Kafka, transforms them, and sends them to Elasticsearch.
- ***Elasticsearch***: stores and analyzes the data received from Logstash.
- ***Kibana***: allows visualization of the data stored in Elasticsearch including the evidence collected by Packetbeat and Auditbeat, and filtering possible attacks.
- ***MongoDB***: database to store the data collected by Packetbeat and Auditbeat.
- ***Mongo Express***: a user interface to manage MongoDB and execute queries.
- ***Java application***: consumes data from Kafka and stores it to MongoDB.

![](<https://github.com/enrimon15/attack-detection-elk/blob/main/images/architecture.png>)

---

## Metasploit Attack

First we run a pre-exploit through the network discovery tool **Nmap** that verifies the connection with the target system and identifies the server type on a given port.
once we have identified that the tomcat server on port 8080 is a vulnerable server, we can run the first exploit module **tomcat_mgr_login** which, using a bruteforce, identifies the access credentials to the tomcat manager dashboard (a dashboard for deploying applications).
Through these credentials run the second exploit **tomcat_mgr_deploy** which deploys a WAR containing a malicious application to obtain complete remote access (reverse shell) to the target system.
Finally, through the **sshkey_persistence** module, an ssh key is added to the root user in order to allow a remote login in the future, with root permissions, without using a password.

--------

## Kibana Dashboard

![](<https://github.com/enrimon15/attack-detection-elk/blob/main/images/dashboard.png>)

----------

## Guide

To run:
- Install Docker. See [here](https://docs.docker.com/get-docker/).
- Go inside docker folder: `cd docker`
- Run the sh script `./start.sh` to set up the infrastucture.
- Simulate the attack (I will create a script to run it automatically). See steps [here](https://github.com/enrimon15/attack-detection-elk/blob/main/attack-guide.md)
- Open kibana [here](http://localhost:5601/app/dashboards#/view/0b05e2c0-de92-11ed-9050-0fc335f87e2c?_g=(filters:!(),refreshInterval:(pause:!t,value:0),time:(from:now-15m,to:now))) and check the charts.
- If you want to check and query data on MongoDb you can open Mongo Express [here](http://localhost:8081) and login with these credentials: monitor-user, monitor-password.

If script `./start.sh` returns an error try to increase sleep seconds inside the script.

For details on attack detection, see [here](https://github.com/enrimon15/attack-detection-elk/blob/main/attack-detection.md)


