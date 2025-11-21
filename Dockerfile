# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia Maven Wrapper e pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Dá permissão de execução ao mvnw e baixa dependências
RUN chmod +x mvnw && ./mvnw -q dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Build do projeto (gera o .jar em target/)
RUN ./mvnw clean package -DskipTests

# Porta que a aplicação usa (Render seta a env PORT, mas o EXPOSE é só informativo)
EXPOSE 8080

# Comando de start
ENTRYPOINT ["java", "-jar", "target/ApiTechOne-0.0.1-SNAPSHOT.jar"]
