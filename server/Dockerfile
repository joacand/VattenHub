FROM maven:3.5-jdk-9 AS builder
RUN java -version

COPY pom.xml /tmp/pom.xml
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve

COPY . /usr/src/vattenhub/
WORKDIR /usr/src/vattenhub/

RUN mvn package

FROM openjdk:9-jre
WORKDIR /root/
COPY --from=builder /usr/src/vattenhub/target/vattenhub-0.1.9.jar .

VOLUME /tmp
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=production","vattenhub-0.1.9.jar"]