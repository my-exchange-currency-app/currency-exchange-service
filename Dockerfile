FROM openjdk:8-jdk-alpine
LABEL maintainer="ahmedbaz1024"
WORKDIR /usr/local/bin/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} currency-exchange-service.jar
EXPOSE 8000
CMD ["java","-Dspring.profiles.active=test","-jar","currency-exchange-service.jar"]
