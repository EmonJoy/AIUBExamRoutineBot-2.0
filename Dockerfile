FROM openjdk:17
WORKDIR /app
COPY myExamRoutine.jar app.jar
CMD ["java", "-jar", "app.jar"]
