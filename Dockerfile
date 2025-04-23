FROM openjdk:8-jre-alpine
WORKDIR /app
COPY app.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]