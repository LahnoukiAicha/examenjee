FROM tomcat:10-jdk19-openjdk

COPY ./target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]