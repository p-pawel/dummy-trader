FROM maven:3-openjdk-17-slim as maven
WORKDIR /home/app
COPY ./ /home/app/
RUN mvn package


FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /home/app
COPY --from=maven /home/app/${JAR_FILE} /home/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
