
# Stage 1: Build the Angular application
FROM node:19 AS build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json (if available) to the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Use the npm script to build the application for production
# You might need to modify "build" if your npm script has a different name
 RUN ng build
# Stage 2: Serve the application with Nginx
FROM nginx:alpine

# Copy the built application from the build stage to the Nginx web server's root directory
# Modify the source path based on the outputPath defined in angular.json
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx when the container has provisioned.
CMD ["nginx", "-g", "daemon off;"]

