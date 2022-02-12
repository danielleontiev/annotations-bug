ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "annotations-bug"
  )
  .aggregate(core, generic)

lazy val core = (project in file("core"))
  .settings(
    name := "core"
  )
  .dependsOn(generic)

lazy val generic = (project in file("generic")).settings(
  name := "generic",
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )
)
