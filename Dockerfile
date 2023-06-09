FROM openjdk:11-jdk-slim as builder
ENV HOME=/app

WORKDIR $HOME
COPY gradlew build.gradle settings.gradle $HOME/
COPY gradle/ $HOME/gradle/
COPY src/ $HOME/src/
RUN ./gradlew clean build -x test -PmainClass=com.b4after.bntquestion.BntQuestionApplication

FROM openjdk:11-jdk-slim
ENV HOME=/app
ARG ARTIFACT_NAME=app.jar
ARG JAR_FILE_PATH=build/libs/bnt-question-0.0.1-SNAPSHOT.jar

WORKDIR $HOME
COPY --from=builder $HOME/$JAR_FILE_PATH $ARTIFACT_NAME
ENTRYPOINT ["java", "-jar", "/app.jar"]
