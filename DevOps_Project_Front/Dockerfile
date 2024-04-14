# Stage 1: Build the Angular application
FROM node:16 as build-step
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Stage 2: Serve the application from Nginx
FROM nginx:alpine
COPY --from=build-step /app/dist/DevOps_Project_Front /usr/share/nginx/html
