
FROM eclipse-temurin:25-jdk
WORKDIR /app

# Copiar código
COPY . .

# Permisos para Gradle
RUN chmod +x ./gradlew

# MODO MEMORIA EXTREMA PARA RENDER FREE TIER
RUN ./gradlew build -x test --no-daemon --max-workers=1 -Dorg.gradle.jvmargs="-Xmx192m -XX:MaxMetaspaceSize=128m -XX:+UseSerialGC"

# ELIMINAR EL JAR SECUNDARIO Y RENOMBRAR EL PRINCIPAL
RUN rm -f build/libs/*-plain.jar
RUN cp build/libs/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Encender la aplicación
ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]