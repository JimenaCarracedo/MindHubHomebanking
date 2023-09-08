FROM jdk11-alpine
FROM gradle:8.2.1 AS BUILD
COPY . .
RUN gradle build
COPY ${JAR_FILE} homebanking-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/build/libs/homebanking-0.0.1-SNAPSHOT.jar"]
