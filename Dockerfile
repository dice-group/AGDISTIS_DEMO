FROM maven:3 AS builder

WORKDIR /project

ADD pom.xml /project/pom.xml
RUN mvn dependency:resolve

ADD . /project
RUN mvn package

FROM openjdk:8-jdk-alpine as runtime

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=builder /project/target/*.jar /mag-demo.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/mag-demo.jar"]
