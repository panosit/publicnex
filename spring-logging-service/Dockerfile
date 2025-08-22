# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set working directory inside the container
WORKDIR /app

# Copy the jar file from target directory
COPY target/spring-logging-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8081
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]