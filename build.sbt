name := """ki-asssets-management-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "org.springframework" % "spring-context" % "4.0.5.RELEASE",
  "com.google.guava" % "guava" % "16.0.1",
  "com.typesafe.play.plugins" %% "play-plugins-mailer" % "2.3.0",
  "org.kohsuke" % "github-api" % "1.55"
)
