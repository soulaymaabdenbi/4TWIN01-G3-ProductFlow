# Use the openjdk11 image from Docker Hub
FROM openjdk:11
# Expose the ports for both Spring Boot and Angular (if needed)
EXPOSE 8080 8085

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR into the container
COPY DevOps_Project/target/DevOps_Project-1.0.jar app.jar

# Copy the compiled Angular frontend files into the container
COPY dist /app/dist

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
