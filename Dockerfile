FROM gradle:7.4.0-jdk17

WORKDIR /app

COPY /app .

RUN chmod +x gradlew

RUN ./gradlew run

CMD ./build/install/app/bin/app