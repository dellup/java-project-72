FROM eclipse-temurin:21-jdk

WORKDIR /app

RUN ./gradlew --no-daemon dependencies

COPY src src

RUN ./gradlew --no-daemon build

ENV JAVA_OPTS "-Xmx512M -Xms512M"
EXPOSE 7060

CMD java -jar build/libs/HexletJavalin-1.0-SNAPSHOT-all.jar