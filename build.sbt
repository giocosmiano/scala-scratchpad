name := "scala-scratchpad"

version := "0.1"

scalaVersion := "2.11.12"

resolvers ++= Seq(Resolver.DefaultMavenRepository, Resolver.mavenLocal)

val scalazVersion = "7.3.0-M21"
val scalazStreamVersion = "0.8.6"
val scalazTypeLevelVersion = "7.1.17"
val sparkVersion = "2.2.0"
val hadoopVersion = "2.6.0"

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

  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "runtime",
  "org.apache.spark" %% "spark-graphx" % sparkVersion,

  "org.apache.hadoop" % "hadoop-common" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-client" % hadoopVersion,

  "org.apache.avro" % "avro" % "1.8.2",
  "org.apache.parquet" % "parquet-avro" % "1.10.0",
  
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",

  "joda-time" % "joda-time" % "2.9.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

scalacOptions += "-feature"

initialCommands in console := "import scalaz._, Scalaz._"
