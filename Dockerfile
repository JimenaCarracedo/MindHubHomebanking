FROM openjdk:8
FROM gradle:8.2.1 AS BUILD
COPY . .
COPY . /build/libs/homebanking-0.0.1-SNAPSHOT.jar
RUN gradle build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "homebanking-0.0.1.SNAPSHOT.jar"]
