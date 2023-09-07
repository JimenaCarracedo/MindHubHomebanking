FROM openjdk:8
COPY . .
RUN gradle clean build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/homebanking.0.0.1.SNAPSHOT.jar"]
