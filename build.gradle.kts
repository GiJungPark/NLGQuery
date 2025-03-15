plugins {
    kotlin("jvm") version "2.1.0"
}

group = "io.gijung"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.zaxxer:HikariCP:3.2.0")
    implementation("com.mysql:mysql-connector-j:9.2.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
