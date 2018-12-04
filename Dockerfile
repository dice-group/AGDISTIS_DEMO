FROM tomcat:8-jre8

# ADD war file
ADD target/mag-1.0.0-BUILD-SNAPSHOT.war webapps/mag-demo.war
