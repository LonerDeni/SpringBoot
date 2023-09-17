FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8081

COPY target/SpringBoot-0.0.1-SNAPSHOT.jar springBoot.jar

CMD ["java", "-jar","springBoot.jar"]