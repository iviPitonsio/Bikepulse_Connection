FROM eclipse-temurin:21
WORKDIR /app
COPY . /app
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
CMD ["java", "-jar", "target/bikepulse-api-0.0.1-SNAPSHOT.jar"]