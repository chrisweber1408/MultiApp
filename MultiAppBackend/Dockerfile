FROM openjdk:17
WORKDIR /app
COPY . .
RUN ./gradlew build -x test
CMD ["java", "-jar", "build/libs/MultiAppBackend-0.0.1-SNAPSHOT.jar"]
