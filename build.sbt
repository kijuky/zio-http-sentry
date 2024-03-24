ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "zio-http-sentry",
    idePackagePrefix := Some("com.example"),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-http" % "3.0.0-RC5",
      "dev.zio" %% "zio-schema-json" % "1.0.1",
      "dev.zio" %% "zio-schema-protobuf" % "1.0.1",
      "io.sentry" % "sentry" % "7.2.0",
      "org.unbescape" % "unbescape" % "1.1.6.RELEASE"
    ),
    fork := true
  )

Global / excludeLintKeys ++= Set(idePackagePrefix)
