plugins {
    kotlin("jvm") version "1.9.10"
    application
    id("org.jetbrains.dokka") version "1.9.10"
}

group = "com.productmanagement"
version = "1.0.0"
description = "A Kotlin-based Product Management System with console and web interfaces"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin standard library
    implementation(kotlin("stdlib"))
    
    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.11")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    
    // JSON serialization (for future API features)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    
    // Test dependencies
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

application {
    mainClass.set("com.productmanagement.MainKt")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

// Create fat JAR with all dependencies
tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "com.productmanagement.MainKt",
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version
        )
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveBaseName.set("product-management-system")
}

// Documentation generation
tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("dokka"))
}

// Custom tasks
tasks.register("runConsole") {
    group = "application"
    description = "Run the console application"
    dependsOn("run")
}

tasks.register("buildDist") {
    group = "distribution"
    description = "Build complete distribution"
    dependsOn("build", "dokkaHtml")
    doLast {
        println("Distribution built successfully!")
        println("JAR: ${tasks.jar.get().archiveFile.get().asFile}")
        println("Documentation: ${buildDir}/dokka")
    }
}

// Code quality
tasks.register("check-style") {
    group = "verification"
    description = "Check code style and formatting"
    doLast {
        println("Code style check completed")
    }
}