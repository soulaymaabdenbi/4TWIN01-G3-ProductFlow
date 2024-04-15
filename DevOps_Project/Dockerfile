# Use the openjdk11 image from Docker Hub
FROM openjdk:11

# Expose the port of your Spring Boot application
EXPOSE 8080

# Copy the jar file from your target folder to the image
COPY target/app.jar /app/app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]