plugins {
    application
    checkstyle
    jacoco
    id("io.freefair.lombok") version "8.6"
    id("java")
    id("com.github.ben-manes.versions") version "0.51.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"

}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.h2database:h2:2.3.232")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation("gg.jte:jte:3.1.16")
    implementation("gg.jte:jte-kotlin:3.1.16") // Если проект на Kotlin
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("io.javalin:javalin:6.1.3")
    implementation("io.javalin:javalin-bundle:6.1.3")
    implementation("io.javalin:javalin-rendering:6.1.6")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation(platform("com.konghq:unirest-java-bom:4.4.5"))
    implementation("com.konghq:unirest-java-core")
    implementation("com.konghq:unirest-modules-jackson")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2") // JUnit 5
    testImplementation("org.mockito:mockito-core:5.3.1") // Mockito
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation(platform("org.junit:junit-bom:5.11.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    implementation("org.jsoup:jsoup:1.17.2")
}

jacoco {
    toolVersion = "0.8.10" // Укажите актуальную версию JaCoCo
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(true) // Генерация отчета в формате XML
        csv.required.set(false) // Отключение отчета в формате CSV
        html.required.set(true) // Генерация отчета в формате HTML
    }
}