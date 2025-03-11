FROM gradle:7.4.0-jdk17

WORKDIR /app

COPY /app .

RUN chmod +x gradlew
RUN ./gradlew run

CMD ./build/install/app/bin/app
ENV JAVA_OPTS "-Xmx512M -Xms512M"
EXPOSE 7060

CMD java -jar build/libs/HexletJavalin-1.0-SNAPSHOT-all.jar