rootProject.name = "lsbasi"

include(
  "part1",
  "part2",
  "part3",
  "part4",
  "part5",
  "part6",
  "part7"
)

for (project in rootProject.children) {
  project.apply {
    projectDir = file("subprojects/$name")
    buildFileName = "$name.gradle.kts"
    require(projectDir.isDirectory) { "Project '${project.path} must have a $projectDir directory" }
    require(buildFile.isFile) { "Project '${project.path} must have a $buildFile build script" }
  }
}