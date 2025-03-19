check-deps:
	cd app && ./gradlew dependencyUpdates -Drevision=release

dev:
	cd app && ./gradlew run

setup:
	cd app && gradle wrapper --gradle-version 8.12

clean:
	cd app && ./gradlew clean

build:
	cd app && ./gradlew clean build

start: dev

install:
	cd app && ./gradlew installDist

lint:
	cd app && ./gradlew checkstyleMain checkstyleTest

test:
	cd app && ./gradlew test

image-build:
	docker build -t hexletcomponents/java-javalin-example:latest .

image-push:
	docker push hexletcomponents/java-javalin-example:latest

.PHONY: build