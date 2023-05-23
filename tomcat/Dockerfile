# Dockerfile
FROM tomcat:9.0
# COPY ./webapps $CATALINA_HOME/webapps
# RUN apt-get update
RUN export CLASSPATH=/usr/local/tomcat/webapps/library_management_system_bc/WEB-INF/src/postgresql-42.6.0.jar:$CLASSPATH
RUN export CLASSPATH=/usr/local/tomcat/lib/servlet-api.jar:$CLASSPATH
# RUN apt-get upgrade gradle
WORKDIR /usr/local/tomcat/webapps
# RUN jar -xvf sample.war
# ADD ./server/src/*.jar app.jarpos