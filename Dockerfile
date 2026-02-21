FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/financial-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5477

ENTRYPOINT ["java", "-jar", "app.jar"]
