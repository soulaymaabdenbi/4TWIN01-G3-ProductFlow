
# Stage 1: Build the Angular application
FROM node:19 AS build

# Set the working directory in the container
WORKDIR /app
# Copier les fichiers package.json et package-lock.json dans le répertoire de travail
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Installer Angular CLI globalement dans l'image
RUN npm install -g @angular/cli

# Copier les fichiers du projet Angular dans le répertoire de travail
COPY . .

# Utiliser le CLI Angular pour construire l'application Angular
RUN ng build --prod

# Utiliser une image légère contenant Nginx pour servir l'application construite
FROM nginx:alpine

# Copier les fichiers de l'application construite depuis le conteneur de construction vers le conteneur Nginx
# Assurez-vous que le chemin d'accès au répertoire de distribution est correct
COPY --from=build /app/dist/summer-workshop-angular /usr/share/nginx/html

# Exposer le port 80 pour que l'application puisse être accessible
EXPOSE 80

# Commande pour démarrer Nginx lors de l'exécution du conteneur
CMD ["nginx", "-g", "daemon off;"]

