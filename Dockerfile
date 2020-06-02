FROM openjdk:11-jdk

EXPOSE 8080
MAINTAINER JULIAN BENSCH

# Add Spring Boot app.jar to Container
COPY /backend/target/backend-0.0.1-SNAPSHOT.jar /app.jar
ENV JAVA_OPTS=""

# Fire up our Spring Boot app by default
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]