FROM jdk11-alpine
ADD target/homebanking-0.0.1-SNAPSHOT.jar homebanking-0.0.1-SNAPSHOT.jar
COPY . .
RUN gradle build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/homebanking-0.0.1.SNAPSHOT.jar"]
