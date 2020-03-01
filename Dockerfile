FROM openjdk:11-jre-slim
VOLUME /tmp
ADD yunbiao-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]