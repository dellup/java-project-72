check-deps:
	cd code/app && chmod +x gradlew && ./gradlew dependencyUpdates -Drevision=release

dev:
	cd code/app && chmod +x gradlew && ./gradlew run

setup:
	cd code/app && chmod +x gradlew && ./gradlew wrapper --gradle-version 8.12

report:
	cd code/app && chmod +x gradlew && ./gradlew jacocoTestReport

clean:
	cd code/app && chmod +x gradlew && ./gradlew clean

build:
	cd code/app && chmod +x gradlew && ./gradlew clean build

start: dev

install:
	cd code/app && chmod +x gradlew && ./gradlew installDist

lint:
	cd code/app && chmod +x gradlew && ./gradlew checkstyleMain checkstyleTest

test:
	cd code/app && chmod +x gradlew && ./gradlew test

image-build:
	docker build -t hexletcomponents/java-javalin-example:latest .

image-push:
	docker push hexletcomponents/java-javalin-example:latest

.PHONY: build