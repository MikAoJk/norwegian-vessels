import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.ServiceFileTransformer
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "no.karveit"
version = "1.0.0-SNAPSHOT"

val commonsCSVVersion = "1.7"
val poiVersion = "4.1.1"
val ktorVersion = "1.4.1"
val logbackVersion = "1.2.3"
val logstashEncoderVersion = "5.1"


plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-mustache:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashEncoderVersion")

    implementation("org.apache.commons:commons-csv:$commonsCSVVersion")
    implementation("org.apache.poi:poi-ooxml:$poiVersion")


}

tasks {

    withType<Jar> {
        manifest.attributes["Main-Class"] = "no.kartveit.BootstrapKt"
    }

    withType<ShadowJar> {
        transform(ServiceFileTransformer::class.java) {
            setPath("META-INF/cxf")
            include("bus-extensions.txt")
        }
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "14"
    }

}