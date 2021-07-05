import sbt._

object Dependencies {

  object Versions {
    val scala211Version              = "2.11.12"
    val scala212Version              = "2.12.13"
    val scala213Version              = "2.13.5"
    val scalaTestVersion             = "3.2.9"
    val logbackVersion               = "1.2.3"
    val scalaCollectionCompatVersion = "2.4.4"
    val dockerJavaVersion            = "3.2.7"
    val progressBarVersion           = "0.9.1"
    val enumeratumVersion            = "1.6.1"
  }

  object scalaLang {

    val scalaCollectionCompat =
      "org.scala-lang.modules" %% "scala-collection-compat" % Versions.scalaCollectionCompatVersion
  }

  object scalatest {
    val scalatest = "org.scalatest" %% "scalatest" % Versions.scalaTestVersion
  }

  object auth0 {
    val javaJwt = "com.auth0" % "java-jwt" % "3.18.0"
  }

}
