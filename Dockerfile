# Usar o JDK 23
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copiar o JAR para dentro do container
COPY target/api_events-0.0.1-SNAPSHOT.jar /app/api_events.jar

# Expor a porta usada pela aplicação (opcional)
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "/app/api_events.jar"]