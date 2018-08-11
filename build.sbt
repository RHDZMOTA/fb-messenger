import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.rhdzmota",
      scalaVersion := "2.12.6",
      version      := "1.0.0"
    )),
    name := "fb-messenger",
    libraryDependencies ++= {
      val circeVersion = "0.9.3"
      Seq(
        "io.circe" %% "circe-core" % circeVersion,
        "io.circe" %% "circe-generic" % circeVersion,
        "io.circe" %% "circe-parser" % circeVersion,
        "io.circe" %% "circe-generic-extras" % circeVersion,
        "org.mongodb.scala" %% "mongo-scala-driver" % "2.3.0",
        "io.netty" % "netty-all" % "4.1.17.Final",
        "org.mongodb" % "bson" % "2.3",
        scalaTest % Test
      )
    }
  )
