# Use the official maven/Java 11 image to create a build artifact.
# This is based on Debian and sets the working directory to /app
# https://hub.docker.com/_/maven
FROM maven:3.8.4-openjdk-11-slim AS build

# Copy the pom.xml file to download dependencies
COPY pom.xml /app/

# Download the dependencies
RUN mvn -f /app/pom.xml dependency:go-offline -B

# Copy the rest of the source code
COPY src /app/src

# Build the application
RUN mvn -f /app/pom.xml clean package -DskipTests

# Use OpenJDK 11 to run the built application
FROM openjdk:11-jre-slim

# Expose the port your app runs on
EXPOSE 8082

# Copy the built artifact from the build stage
COPY --from=build /app/target/DevOps_Project-1.0.jar /app/DevOps_Project-1.0.jar

# Set the working directory
WORKDIR /app

# Run the application
ENTRYPOINT ["java", "-jar", "/app/DevOps_Project-1.0.jar"]