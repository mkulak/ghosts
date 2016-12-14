name := "ghosts"

organization := "com.github.mkulak"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats" % "0.8.1",
  "org.specs2" %% "specs2-core" % "3.8.3" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

