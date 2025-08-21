# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /sb_project
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /sb_project
COPY --from=build /sb_project/target/sb_project-0.0.1-SNAPSHOT.jar appn.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appn.jar"]