import Dependencies._

lazy val advent = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "advent-of-code",
    libraryDependencies += scalaTest % Test
  )
