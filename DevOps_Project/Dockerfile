# Use a base image with OpenJDK 11
FROM openjdk:11

# Expose the port that your Spring Boot application runs on
EXPOSE 8085

# Add the Spring Boot application JAR file into the container
ADD target/DevOps_Project-1.0.jar /app/DevOps_Project-1.0.jar

# Set the working directory in the container
WORKDIR /app

# Specify the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "DevOps_Project-1.0.jar"]
