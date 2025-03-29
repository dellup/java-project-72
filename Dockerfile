FROM gradle:8.7.0-jdk21

WORKDIR /app

COPY /app .

RUN chmod +x gradlew && \
    ./gradlew installDist

CMD ./build/install/app/bin/app