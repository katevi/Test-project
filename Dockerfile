FROM gradle:6.3.0-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar

FROM openjdk:8-jre-alpine

RUN mkdir /mnt/best-subnet-app/
RUN mkdir /mnt/data/
#VOLUME /mnt/data/

COPY in.txt /mnt/data/in.txt

COPY --from=build /home/gradle/src/build/libs/TestProject-1.0-SNAPSHOT-all.jar /mnt/best-subnet-app/TestProject-1.0-SNAPSHOT-all.jar
ENTRYPOINT ["java", "-jar", "//mnt//best-subnet-app//TestProject-1.0-SNAPSHOT-all.jar", "//mnt//data//in.txt"]