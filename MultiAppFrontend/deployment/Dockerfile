# Stage 1: Build Angular Application
FROM node:latest as builder

WORKDIR /app

COPY .. .

RUN npm install
RUN npm run build

# Stage 2: Setup NGINX
FROM nginx:1.21-alpine

# Copy built Angular app
COPY --from=builder /app/dist/multi-app-frontend/browser /usr/share/nginx/html

# Copy custom nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
