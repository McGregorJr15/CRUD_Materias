
FROM eclipse-temurin:25-jdk
WORKDIR /app


COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew build -x test


RUN cp build/libs/*.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]