name := "hello-scala"

version := "1.0"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.1.3" % "test"
)


initialCommands in console := " import example.Answers._"