# Use the official Node.js 16 as a builder stage
FROM node:16 as builder

# Set the working directory inside the builder stage
WORKDIR /usr/src/app

# Copy package.json and package-lock.json (if available)
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the project files into the docker image
COPY . .

# Build the project for production
RUN npm run build

# Use nginx:alpine for the final image
FROM nginx:alpine

# Copy the build output to replace the default nginx contents.
COPY --from=builder /usr/src/app/dist/summer-workshop-angular /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
