# Dockerfile
FROM tomcat:9.0
COPY postgresql-42.6.0.jar $CATALINA_HOME/lib/
ENV CLASSPATH=$CATALINA_HOME/webapps/library_management_system_bc/WEB-INF/classes/library_management_class:$CATALINA_HOME/lib/servlet-api.jar:$CATALINA_HOME/webapps/library_management_system_bc/WEB-INF/classes
WORKDIR /usr/local/tomcat/webapps/library_management_system_bc/WEB-INF/src

