FROM maven:3.8.5-openjdk-17 AS maven_build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim AS runtime
WORKDIR /app
COPY --from=maven_build /app/target/crud_docker_app.jar crud_docker_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "crud_docker_app.jar"]