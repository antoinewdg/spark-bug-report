ThisBuild / scalaVersion     := "2.12.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "bug-repro-v2",
  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.2.0" ,
  "org.apache.spark" %% "spark-sql" % "3.2.0",
)

