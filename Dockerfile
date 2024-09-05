FROM openjdk:17
ARG JAR_FILE
COPY ${JAR_FILE} products.jar
ENTRYPOINT ["java","-jar","/products.jar"]