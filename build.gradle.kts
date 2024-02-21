
plugins {
    kotlin("jvm") version "1.9.22"
    id("org.graalvm.buildtools.native") version "0.10.1"
}

group = "fi.hnybom"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")
    implementation("org.slf4j:slf4j-simple:2.0.12")
    implementation("de.m3y.kformat:kformat:0.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("star")
            mainClass.set("fi.hnybom.terminalstar.MainKt")
        }

    }
    binaries.all {
        buildArgs.add("--verbose")
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}