FROM openjdk:21
EXPOSE 8083
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} formulario.jar
ENTRYPOINT ["java","-jar","/formulario.jar"]   