FROM openjdk:21.0.1

COPY target/crud_docker_app.jar /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","crud_docker_app.jar"]