FROM openjdk:17-oracle
ARG JAR_FILE
COPY ${JAR_FILE} products.jar
ENTRYPOINT ["java","-jar","/products.jar"]