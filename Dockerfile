FROM openjdk:8
RUN build
COPY . .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/homebanking-0.0.1.SNAPSHOT.jar"]
