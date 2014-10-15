name := """play-scala-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(  
  "org.sorm-framework" % "sorm" % "0.3.15",
  "com.h2database" % "h2" % "1.4.177",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "jp.t2v" %% "play2-auth"      % "0.12.0",
  "jp.t2v" %% "play2-auth-test" % "0.12.0" % "test",
  "com.typesafe.slick" %% "slick" % "2.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)     

