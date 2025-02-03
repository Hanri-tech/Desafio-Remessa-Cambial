FROM maven:3.9.7-eclipse-temurin-17 as buildMaven

WORKDIR /build

COPY pom.xml ./
COPY .mvn .mvn

COPY src src

RUN mvn clean install -DskipTests

FROM amazoncorretto:17-alpine

WORKDIR /app

# Copia o JAR gerado no estágio anterior
COPY --from=buildMaven /build/target/*.jar /app/app.jar

EXPOSE 8080

# Definir o comando de execução
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
