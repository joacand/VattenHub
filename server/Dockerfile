FROM openjdk:9-jre
VOLUME /tmp
ADD target/vattenhub-0.1.9.jar target/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=production","target/app.jar"]