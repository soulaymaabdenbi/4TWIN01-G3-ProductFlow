# Use the official Node.js image that is compatible with your Angular version
FROM node:16.14.0 as build

# Set the working directory in the container
WORKDIR /app

# Copy the package.json and package-lock.json
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the entire project to the container
COPY . .

# Use the npm script to build the Angular app for production
RUN npm run build

# Use a smaller, production-ready Nginx image to serve the built app
FROM nginx:1.21.6-alpine

# Copy the production build files from the build stage to the nginx serve folder
COPY --from=build /app/dist/summer-workshop-angular /usr/share/nginx/html

# Expose port 80 for the web server
EXPOSE 80

# Start Nginx and keep the process running
CMD ["nginx", "-g", "daemon off;"]
