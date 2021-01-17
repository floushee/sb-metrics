FROM maven:3.6.3-openjdk-11 AS builder
WORKDIR /build
COPY . /build/
RUN mvn package

FROM openjdk:11
WORKDIR /app/
COPY --from=builder /build/target/sb-metrics-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
