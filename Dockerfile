FROM openjdk:latest

COPY target/assemblylang-0.0.1-SNAPSHOT.jar /app/assemblylang-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "assemblylang-0.0.1-SNAPSHOT.jar"]