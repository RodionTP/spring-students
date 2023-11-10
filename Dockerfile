FROM openjdk:21-oracle
WORKDIR /app
COPY out/artifacts/students_jar/students.jar app.jar
ENV CREATE_STUDENTS=false
ENV COUNT_STUDENTS_CREATE=15
ENTRYPOINT ["java", "-jar", "app.jar"]