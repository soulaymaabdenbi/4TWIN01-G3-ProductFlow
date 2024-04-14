# Use the official Node.js image as the base image
FROM node:19 as build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the entire project to the container
COPY . .

# Build the Angular app for production
RUN ng build --prod --output-path=dist/DevOps_Project_Front

# Use a smaller, production-ready image as the final image
FROM nginx:soulaymaabdenbi-4twin1-g3-frontend

# Copy the production-ready Angular app to the Nginx webserver's root directory
COPY --from=build /app/dist/DevOps_Project_Front /usr/share/nginx/html

# Expose port 80
EXPOSE 8080

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
