
FROM eclipse-temurin:25-jdk
WORKDIR /app


COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon -Dorg.gradle.jvmargs="-Xmx300m"

RUN cp build/libs/*.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]