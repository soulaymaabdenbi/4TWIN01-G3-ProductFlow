# Use the openjdk11 image from Docker Hub
FROM openjdk:11
# Expose the port of your Spring Boot application
EXPOSE 8082
# Copy the jar of the application into the container
ADD target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
ENTRYPOINT ["java","-jar","/DevOps_Project-1.0.jar"]
