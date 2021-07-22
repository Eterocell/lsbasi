plugins {
  application
  kotlin("jvm")
}

repositories {
  google()
  mavenCentral()
}

dependencies {
  // Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
  // Use the Kotlin JDK 8 standard library.
  implementation("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")

  // JUnit 5
  testImplementation(
    "org.junit.jupiter",
    "junit-jupiter-api",
    Versions.junitVersion
  )
  testRuntimeOnly(
    "org.junit.jupiter",
    "junit-jupiter-engine",
    Versions.junitVersion
  )
  testImplementation(
    "org.junit.jupiter",
    "junit-jupiter",
    Versions.junitVersion
  )

}

tasks {
  compileKotlin {
    kotlinOptions {
      jvmTarget = "11"
    }
  }

  compileTestKotlin {
    kotlinOptions {
      jvmTarget = "11"
    }
  }

  getByName<Test>("test") {
    useJUnitPlatform()
  }
}
