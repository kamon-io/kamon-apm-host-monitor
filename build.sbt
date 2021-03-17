name := "host-metrics-server"
organization := "kamino"

scalaVersion := "2.13.5"
scalacOptions := Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xcheckinit",
  "-Xverify"
)

resolvers += Resolver.bintrayRepo("kamon-io", "releases")
resolvers += Resolver.mavenLocal

//version in Docker := scala.sys.process.Process("git rev-parse HEAD").lineStream.head

val armeriaVersion = "1.5.0"
val kamonVersion = "2.1.12"

libraryDependencies ++= Seq(
  "com.github.pureconfig"      %% "pureconfig"           % "0.14.1",
  "ch.qos.logback"              % "logback-classic"      % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging"        % "3.9.2",
  "io.kamon"                   %% "kamon-core"           % kamonVersion,
  "io.kamon"                   %% "kamon-system-metrics" % kamonVersion,
  "io.kamon"                   %% "kamon-apm-reporter"   % kamonVersion,
  "com.linecorp.armeria"        % "armeria"              % armeriaVersion,
  "com.linecorp.armeria"       %% "armeria-scalapb"      % armeriaVersion,
)

enablePlugins(JavaAppPackaging)
