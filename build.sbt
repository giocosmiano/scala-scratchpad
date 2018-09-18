name := "la-sbt-demo"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += Resolver.mavenLocal

val scalazVersion = "7.3.0-M11"
val scalazStreamVersion = "0.8.6"
val scalazTypeLevelVersion = "7.1.17"

// repository can be found here --> http://rdmaven.int.westgroup.com:8081/nexus/content/groups/public
// scala logging --> https://github.com/lightbend/scala-logging

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-iteratee" % scalazVersion,
  "org.scalaz" %% "scalaz-typelevel" % scalazTypeLevelVersion,
  "org.scalaz.stream" %% "scalaz-stream" % scalazStreamVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test",
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % " test",
  "joda-time" % "joda-time" % "2.9.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
)

scalacOptions += "-feature"

initialCommands in console := "import scalaz._, Scalaz._"
