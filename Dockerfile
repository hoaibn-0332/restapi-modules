FROM openjdk:17-alpine
WORKDIR /app
COPY app/build/libs/app-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]