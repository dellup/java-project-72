FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY gradlew /app/
COPY gradle /app/gradle/
COPY build.gradle /app/
COPY settings.gradle /app/

RUN chmod +x gradlew
RUN ./gradlew --no-daemon dependencies
RUN ./gradlew --no-daemon build

ENV JAVA_OPTS "-Xmx512M -Xms512M"
EXPOSE 7060

CMD java -jar build/libs/HexletJavalin-1.0-SNAPSHOT-all.jar