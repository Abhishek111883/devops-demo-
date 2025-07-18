# Use official Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jdk as builder

# Set working directory in container
WORKDIR /app

# Copy Gradle/Maven build files and source code
COPY . .

# If using Gradle (replace with Maven steps if needed)
RUN ./gradlew build -x test

# ---- Production Stage ----
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy built jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
