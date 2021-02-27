FROM gradle:6.3.0-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle jar

FROM openjdk:8-jre-slim

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/TestProject-1.0-SNAPSHOT.jar /app/TestProject-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/TestProject-1.0-SNAPSHOT.jar"]