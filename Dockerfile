FROM openjdk:17-alpine
WORKDIR opt/app

COPY /build/libs/WorkersManager-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]