FROM gradle:8.1.1-jdk11-alpine
COPY . .
RUN gradle clean build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/homebanking.0.0.1.SNAPSHOT.jar"]