
plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.3.2"
}



group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor server dependencies (same version for consistency)
    implementation("io.ktor:ktor-server-core:2.3.12")  // Ktor core library
    implementation("io.ktor:ktor-server-content-negotiation:2.3.12")  // Content negotiation (optional, for JSON support)
    implementation("io.ktor:ktor-serialization-gson:2.3.12")  // JSON serialization (optional)

    // Kord library (Discord bot library)
    implementation("dev.kord:kord-core:0.14.0")
    implementation("dev.kord:kord-gateway:0.14.0")
    implementation("io.ktor:ktor-server-netty:2.3.12")
    // Test dependencies
    testImplementation(kotlin("test"))
}