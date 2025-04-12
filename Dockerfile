FROM eclipse-temurin:22-jdk

WORKDIR /app

COPY target/myExamRoutine.jar .

CMD ["java", "-jar", "myExamRoutine.jar"]
