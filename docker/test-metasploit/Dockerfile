FROM ubuntu:20.04

# package required
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
        openjdk-8-jre \
        openssh-server \
        curl \
        && rm -rf /var/lib/apt/lists/*

# packetbeat
RUN curl -L -O https://artifacts.elastic.co/downloads/beats/packetbeat/packetbeat-7.14.0-amd64.deb && \
    dpkg -i packetbeat-7.14.0-amd64.deb && \
    rm -f packetbeat-7.14.0-amd64.deb

# Tomcat 6
RUN curl -L -O https://archive.apache.org/dist/tomcat/tomcat-6/v6.0.53/bin/apache-tomcat-6.0.53.tar.gz && \
    tar xvzf apache-tomcat-6.0.53.tar.gz && \
    rm -f apache-tomcat-6.0.53.tar.gz && \
    mv apache-tomcat-6.0.53 /usr/local/tomcat && \
    chmod +x /usr/local/tomcat/bin/*

# ssh
RUN mkdir /var/run/sshd && \
    echo 'root:root' | chpasswd && \
    sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# permissions configurantion packetbeat
RUN chown root /etc/packetbeat/packetbeat.yml && chmod go-w /etc/packetbeat/packetbeat.yml
    

COPY packetbeat.yml /etc/packetbeat/packetbeat.yml
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

EXPOSE 8080 22

CMD service ssh start && catalina.sh run
