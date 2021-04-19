import Dependencies._

def crossScalacOptions(scalaVersion: String): Seq[String] = CrossVersion.partialVersion(scalaVersion) match {
  case Some((2L, scalaMajor)) if scalaMajor >= 12 =>
    Seq.empty
  case Some((2L, scalaMajor)) if scalaMajor <= 11 =>
    Seq("-Yinline-warnings")
}

lazy val baseSettings = Seq(
  organization := "com.github.j5ik2o",
  homepage := Some(url("https://github.com/j5ik2o/sw4jj")),
  licenses := List("The MIT License" -> url("http://opensource.org/licenses/MIT")),
  developers := List(
    Developer(
      id = "j5ik2o",
      name = "Junichi Kato",
      email = "j5ik2o@gmail.com",
      url = url("https://blog.j5ik2o.me")
    )
  ),
  scalaVersion := Versions.scala211Version,
  crossScalaVersions := Seq(Versions.scala211Version, Versions.scala212Version, Versions.scala213Version),
  scalacOptions ++= (Seq(
    "-feature",
    "-deprecation",
    "-unchecked",
    "-encoding",
    "UTF-8",
    "-language:_",
    "-Ydelambdafy:method",
    "-target:jvm-1.8",
    "-Yrangepos",
    "-Ywarn-unused"
  ) ++ crossScalacOptions(scalaVersion.value)),
  libraryDependencies ++= Seq(
    scalatest.scalatest % Test
  ),
  ThisBuild / scalafixScalaBinaryVersion := CrossVersion.binaryScalaVersion(scalaVersion.value),
  semanticdbEnabled := true,
  semanticdbVersion := scalafixSemanticdb.revision,
  Test / publishArtifact := false,
  Test / fork := true,
  Test / parallelExecution := false
)

val root = (project in file("."))
  .settings(baseSettings)
  .settings(
    name := "sw4jj",
    libraryDependencies ++= Seq(
      auth0.javaJwt
    ),
    libraryDependencies ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2L, scalaMajor)) if scalaMajor == 13 =>
          Seq.empty
        case Some((2L, scalaMajor)) if scalaMajor <= 12 =>
          Seq(
            scalaLang.scalaCollectionCompat
          )
      }
    }
  )

// --- Custom commands
addCommandAlias("lint", ";scalafmtCheck;test:scalafmtCheck;scalafmtSbtCheck;scalafixAll --check")
addCommandAlias("fmt", ";scalafmtAll;scalafmtSbt")
