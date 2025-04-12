FROM eclipse-temurin:22-jdk

WORKDIR /app

COPY myExamRoutine.jar .

CMD ["java", "-jar", "myExamRoutine.jar"]
