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
        scalaTest % Test
      )
    }
  )
