FROM maven:3.8.5-openjdk:17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim AS build
COPY --from=build /target/crud_docker_app.jar crud_docker_app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","crud_docker_app.jar"]