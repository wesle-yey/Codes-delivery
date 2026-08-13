# Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy pom.xml for dependency caching
COPY pom.xml .

# Download dependencies using BuildKit cache mount
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B || true

# Copy source code and build jar
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy built application JAR
COPY --from=build /app/target/*.jar app.jar

# Create non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
