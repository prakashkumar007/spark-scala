import Dependencies._

ThisBuild / organization := "spark-poc"
ThisBuild / version := "0.1-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.9"

lazy val root = (project in file("."))
  .enablePlugins(SparkPlugin)
  .settings(
    name := "spark-poc",
    libraryDependencies ++= backendDependencies ++ testDependencies,
    scalacOptions := List(
      "-encoding",
      "utf8",
      "-Xfatal-warnings",
      "-deprecation",
      "-unchecked"
    )
  )
