# Multi-stage build for Spring Boot application
# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copy Maven wrapper and pom files
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Fix Windows line endings (CRLF -> LF) and ensure mvnw is executable
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# Copy module POMs
COPY to-do-app-core/pom.xml to-do-app-core/
COPY to-do-app-infra-spring/pom.xml to-do-app-infra-spring/

# Create dummy pom.xml for quarkus module to satisfy parent pom validation
RUN mkdir -p to-do-app-infra-quarkus && \
    echo '<?xml version="1.0" encoding="UTF-8"?><project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"><modelVersion>4.0.0</modelVersion><parent><groupId>com.mello</groupId><artifactId>to-do-app-backend</artifactId><version>1.0-SNAPSHOT</version></parent><artifactId>to-do-app-infra-quarkus</artifactId></project>' > to-do-app-infra-quarkus/pom.xml

# Download dependencies (cached layer) - build only core and spring modules
RUN ./mvnw dependency:go-offline -B -pl to-do-app-core,to-do-app-infra-spring || true

# Copy source code
COPY to-do-app-core/src to-do-app-core/src
COPY to-do-app-infra-spring/src to-do-app-infra-spring/src

# Build application - build only core and spring modules (skip tests, force update, clean install)
RUN ./mvnw clean install -DskipTests -B -U -pl to-do-app-core,to-do-app-infra-spring

# Stage 2: Runtime;~´-=
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="victor.mello"
LABEL description="To-Do App Backend - Clean Architecture with Spring Boot"

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/to-do-app-infra-spring/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# JVM optimization flags
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:InitialRAMPercentage=50.0 \
               -XX:+UseG1GC \
               -XX:+UseStringDeduplication \
               -Djava.security.egd=file:/dev/./urandom"

# Run application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
