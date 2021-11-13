FROM bellsoft/liberica-openjdk-alpine:11

ARG TOKEN_ARG
ENV token=$TOKEN_ARG

ARG JAR_FILE=build/libs/faceitstatsok-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
