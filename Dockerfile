# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the source code into the container
COPY . /app

# Compile the Java source files
RUN javac ChatServer.java ChatClient.java

# Expose the port your server will run on (e.g., 1201 from your code)
# Change the port number if your application uses a different one
EXPOSE 1201

# Define the command to run the application
CMD ["java", "ChatServer"]