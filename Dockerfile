FROM gradle:8.7.0-jdk21 AS builder

WORKDIR /app

COPY /app .

RUN gradle installDist

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/build/install/app /app

CMD ./build/install/app/bin/app