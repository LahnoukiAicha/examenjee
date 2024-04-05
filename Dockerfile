# Use the official Tomcat image with Java 11 JDK
FROM tomcat:10-jdk11-openjdk

# Copy the .war file from the local filesystem into the Tomcat webapps directory
COPY ./target/jpajsf-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080 to allow external access to the web application
EXPOSE 8080

# Start Tomcat and deploy the web application when the container starts
CMD ["catalina.sh", "run"]
