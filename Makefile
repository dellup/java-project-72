check-deps:
	cd app && chmod +x gradlew && ./gradlew dependencyUpdates -Drevision=release

dev:
	cd app && chmod +x gradlew && ./gradlew run

setup:
	cd app && chmod +x gradlew && gradle wrapper --gradle-version 8.12

clean:
	cd app && chmod +x gradlew && ./gradlew clean

build:
	cd app && chmod +x gradlew && ./gradlew clean build

start: dev

install:
	cd app && chmod +x gradlew && ./gradlew installDist

lint:
	cd app && chmod +x gradlew && ./gradlew checkstyleMain checkstyleTest

test:
	cd app && chmod +x gradlew && ./gradlew test

image-build:
	docker build -t hexletcomponents/java-javalin-example:latest .

image-push:
	docker push hexletcomponents/java-javalin-example:latest

.PHONY: build