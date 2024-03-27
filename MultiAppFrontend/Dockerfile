# Stage 1: Build Angular Application
FROM node:latest as builder

WORKDIR /app

COPY . .

# Install dependencies
RUN npm install

# Build the Angular app
RUN npm run build

# Stage 2: Setup NGINX
FROM nginx:1.21-alpine

# Copy built Angular app from the previous stage
COPY --from=builder /app/dist/multi-app-frontend/browser /usr/share/nginx/html

EXPOSE 80
