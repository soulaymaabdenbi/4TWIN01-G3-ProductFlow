
# Utiliser une image de base contenant Node.js pour exécuter Angular
FROM node:latest as build

# Définir le répertoire de travail dans lequel l'application Angular sera construite
WORKDIR /app

# Copier les fichiers du projet Angular dans le répertoire de travail
COPY . .

# Installer les dépendances et construire l'application Angular
RUN npm install && npm run build --prod

# Utiliser une image légère contenant Nginx pour servir l'application construite
FROM nginx:alpine

# Copier les fichiers de l'application construite depuis le conteneur de construction vers le conteneur Nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Exposer le port 80 pour que l'application puisse être accessible
EXPOSE 80

# Commande pour démarrer Nginx lors de l'exécution du conteneur
CMD ["nginx", "-g", "daemon off;"]
