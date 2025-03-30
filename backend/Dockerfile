FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/backend-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#FROM maven:3.8.1-openjdk-17
#
#WORKDIR /app
#COPY . .
#RUN mvn clean package
#
#CMD mvn spring-boot:run