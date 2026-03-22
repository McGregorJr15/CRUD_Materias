
FROM eclipse-temurin:25-jdk
WORKDIR /app


COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon --max-workers=1 -Dorg.gradle.jvmargs="-Xmx192m -XX:MaxMetaspaceSize=128m -XX:+UseSerialGC"

RUN cp build/libs/*.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]