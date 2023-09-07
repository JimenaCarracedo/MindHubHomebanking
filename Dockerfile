FROM openjdk:8
COPY build/libs/homebanking.0.0.1.SNAPSHOT.jar
RUN gradle build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/HomebankingApplication.0.0.1.SNAPSHOT.jar"]
