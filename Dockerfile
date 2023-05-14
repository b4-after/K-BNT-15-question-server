FROM openjdk:11-jdk-slim as builder
ENV HOME=/app

WORKDIR $HOME
COPY gradlew build.gradle settings.gradle $HOME
COPY gradle/ $HOME/gradle
RUN chmod +x gradlew
RUN ./gradlew build -x test --debug

COPY src $HOME/src
RUN ./gradlew clean build -x test

FROM openjdk:17.0.2-jdk
ENV HOME=/app
ARG ARTIFACT_NAME=app.jar
ARG JAR_FILE_PATH=build/libs/bnt-question-0.0.1-SNAPSHOT.jar

WORKDIR $HOME
COPY --from=builder $HOME/$JAR_FILE_PATH $ARTIFACT_NAME
ENTRYPOINT ["java", "-jar", "app.jar"]
