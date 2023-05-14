FROM openjdk:11-jdk-slim as builder

WORKDIR /usr/src/app
COPY . .
RUN ./gradlew build -x test

FROM openjdk:11-jre-slim
WORKDIR /opt/app
COPY --from=builder /usr/src/app/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
