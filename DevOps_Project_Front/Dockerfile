FROM node:latest as build
WORKDIR /app
COPY package.json package-lock.json ./
# Set proxy configuration if necessary
ENV HTTP_PROXY=192.168.56.10:8080
ENV HTTPS_PROXY=192.168.56.10:8080
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist/DevOps_Project_Front /usr/share/nginx/html
