#!/bin/bash

chmod 600 "$1"
ssh -i "$1" "root@$2"

#example execution: ./ssh-connection.sh /root/myKey 172.30.0.6