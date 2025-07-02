# Etapa de construcción (Build Stage)
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app


COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw


RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "app.jar"]