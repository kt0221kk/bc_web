# Dockerfile
FROM tomcat:10.0.14
COPY ./webapps $CATALINA_HOME/webapps
RUN apt-get update
RUN export CLASSPATH=postgresql-42.6.0.jar:$CLASSPATH
# RUN apt-get upgrade gradle
WORKDIR /usr/src
ADD ./server/src/*.jar app.jar
