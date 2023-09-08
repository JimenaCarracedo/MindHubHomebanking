FROM openjdk:8
FROM gradle:8.2.1 AS BUILD
COPY . /homebanking
RUN gradle build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/homebanking/build/libs/homebanking-0.0.1.SNAPSHOT.jar"]
