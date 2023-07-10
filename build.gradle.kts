import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "no.karveit"
version = "1.0.1"


val jvmTargetVersion = "17"


val commonsCSVVersion = "1.9.0"
val poiVersion = "5.2.2"
val ktorVersion = "2.3.2"
val logbackVersion = "1.2.11"
val logstashEncoderVersion = "7.2"
val kotlinVersion = "1.9.0"

val junitJupiterVersion = "5.8.2"


plugins {
    java
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-mustache:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashEncoderVersion")

    implementation("org.apache.commons:commons-csv:$commonsCSVVersion")
    implementation("org.apache.poi:poi-ooxml:$poiVersion")


}

tasks {

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = jvmTargetVersion
    }

    withType<ShadowJar> {
        archiveBaseName.set(project.name)
        mergeServiceFiles()
        manifest.attributes["Main-Class"] = "no.kartveit.BootstrapKt"
    }


    withType<Wrapper> {
        gradleVersion = "7.4.2"
    }

    build {
        dependsOn(shadowJar)
    }

}
