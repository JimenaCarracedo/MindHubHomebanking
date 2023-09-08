FROM gradle:jdk11-alpine AS BUILD_STAGE
COPY . .
RUN gradle build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/homebanking-0.0.1.SNAPSHOT.jar"]
