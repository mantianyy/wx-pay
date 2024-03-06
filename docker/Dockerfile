FROM openjdk:11

WORKDIR /root/deploy-jar/

ADD *.jar  /root/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom/","-Dlogging.file=/var/log/pay-integration/pay-integration.log","-jar","/root/app.jar"]
EXPOSE 8089