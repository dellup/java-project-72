FROM gradle:8.7.0-jdk21 AS builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew installDist

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/build/install/app /app

CMD ["./bin/app"]