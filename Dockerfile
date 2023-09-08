FROM openjdk:8


COPY . .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/homebanking-0.0.1.SNAPSHOT.jar"]
RUN gradle build