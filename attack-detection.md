# Attack Detection

Thanks to Elasticsearch and Kibana, you can query the data collected by Packetbeat and Auditbeat to monitor attacks.
The following queries can be run through the user interface offered by Kibana, or you can consult the related dashboard with the charts created on the basis of the same queries, to view the evidence in a more readable way.


- Query to detect network scanning activity on port 8080 of target container with nmap command (pre-exploit):

    ```javascript
    agent.type: "packetbeat" AND destination.port: 8080 AND destination.domain: "vulnerable-mts" AND http.request.method: "get" AND url.path LIKE '%nmap%' and NOT type: "flow" AND NOT client.ip: ("knownIP1" OR "knownIP2")
    ```

- Query to detect possible bruteforce attack to get tomcat manager dashboard login credentials (first exploit tomcat_mgr_login):

    ```javascript
    agent.type: "packetbeat" AND destination.port: 8080 AND destination.domain: "vulnerable-mts" and NOT type: "flow" AND NOT client.ip: ("knownIP1" OR "knownIP2") AND http.request.method: "get" AND url.path LIKE '%/manager/html%' ANDhttp.response.status_code: 401
    ```

- Query to detect a possible deployment of a malicious file through the tomcat manager dashboard (second exploit tomcat_mgr_deploy):

    ```javascript
    agent.type: "packetbeat" AND destination.port: 8080 AND destination.domain: "vulnerable-mts" AND http.request.method: "put" AND url.path LIKE '%/manager/deploy%' and NOT type: "flow" AND NOT client.ip: ("knownIP1" OR "knownIP2")
    ```

- Query to detect a change in the authorized_keys file integrity related to the opening of a permanent backdoor (latest sshkey_persistence exploit):

    ```javascript
    agent.type: "auditbeat" AND file.path LIKE '%.authorized_keys%' AND event.action LIKE '%updated%'
    ```


![](<https://github.com/enrimon15/attack-detection-elk/blob/main/images/dashboard.png>)