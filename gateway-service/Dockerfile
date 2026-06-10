FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "target/gateway-service-0.0.1-SNAPSHOT.jar"]