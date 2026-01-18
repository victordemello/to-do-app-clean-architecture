# Stage 1: Build
FROM node:20-alpine AS builder

WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build -- --configuration production

# Stage 2: Runtime
FROM nginx:1.25-alpine

LABEL maintainer="victor.mello"
LABEL description="To-Do App Frontend - Angular"

# Copy nginx config template
COPY nginx.conf /etc/nginx/nginx.conf.template

COPY --from=builder /app/dist/to-do-app-web/browser /usr/share/nginx/html

EXPOSE 80

HEALTHCHECK --interval=30s --timeout=3s \
  CMD wget -qO- http://localhost:80/ || exit 1

# Default backend host
ENV BACKEND_HOST=backend-spring

# Use envsubst to replace environment variables in nginx config at runtime
CMD ["/bin/sh", "-c", "envsubst '${BACKEND_HOST}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;'"]
