FROM openjdk:21
EXPOSE 8082
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} microservicioevalucion.jar
ENTRYPOINT ["java","-jar","/microservicioevalucion.jar"]   