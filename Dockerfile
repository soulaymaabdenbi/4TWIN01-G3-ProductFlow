# Use the openjdk11 image from Docker Hub
FROM openjdk:11
# Expose the port of your Spring Boot application
EXPOSE 8085
#RUN apt-get update && apt-get install -y curl
# Copy the jar of the application into the container
ADD DevOps_Project/target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/DevOps_Project-1.0.jar"]

