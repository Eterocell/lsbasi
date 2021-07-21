plugins {
  application
  kotlin("jvm")
}

repositories {
  google()
  mavenCentral()
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

application {
  mainClass.set("com.eterocell.lsbasi.part1.Calc1Kt")
}