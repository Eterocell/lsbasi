plugins {
  application
  kotlin("jvm")
}

repositories {
  google()
  mavenCentral()
}

dependencies {
  // 	Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
  // 	Use the Kotlin JDK 8 standard library.
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
}

tasks {
  compileKotlin {
    kotlinOptions {
      jvmTarget = "11"
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
  }

  compileTestKotlin {
    kotlinOptions {
      jvmTarget = "11"
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
  }

  getByName<Test>("test") {
    useJUnitPlatform()
  }
}

application {
  mainClass.set("com.eterocell.lsbasi.part2.Calc2Kt")
}