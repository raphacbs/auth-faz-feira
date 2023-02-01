FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
ENV DATABASE_HOST=postgresql://172.24.0.2:5432/faz-feira-dev
ENV DATABASE_USER=postgres
ENV DATABASE_PASSWORD=postgres
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]