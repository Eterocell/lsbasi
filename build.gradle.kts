buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
  }
}

allprojects {
  group = AppConfig.group
}

subprojects {
  repositories {
    google()
    mavenCentral()
  }
}

tasks {
  wrapper {
    gradleVersion = "7.1.1"
    distributionType = Wrapper.DistributionType.BIN
  }
}
