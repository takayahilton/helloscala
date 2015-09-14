name := "hello-scala"

version := "1.0"

scalaVersion := "2.11.0"

val scalazVersion = "7.1.0"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.1.3" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.4" % "test",
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full)


scalacOptions ++= Seq(
  "-feature",
  "-language:experimental.macros"
)


initialCommands in console := "import macroHello._"