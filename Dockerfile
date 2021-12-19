FROM vulhub/tomcat:8.0
MAINTAINER Mysticbinary

RUN rm -rf /usr/local/tomcat/webapps/*
COPY ROOT.war /usr/local/tomcat/webapps/

#公开端口
EXPOSE 8080