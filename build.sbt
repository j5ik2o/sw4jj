import Dependencies._

def crossScalacOptions(scalaVersion: String): Seq[String] = CrossVersion.partialVersion(scalaVersion) match {
  case Some((2L, scalaMajor)) if scalaMajor >= 12 =>
    Seq.empty
  case Some((2L, scalaMajor)) if scalaMajor <= 11 =>
    Seq("-Yinline-warnings")
}

lazy val deploySettings = Seq(
  sonatypeProfileName := "com.github.j5ik2o",
  publishMavenStyle := true,
  Test / publishArtifact := false,
  pomIncludeRepository := { _ => false },
  pomExtra := {
    <url>https://github.com/j5ik2o/sw4jj</url>
      <licenses>
        <license>
          <name>The MIT License</name>
          <url>http://opensource.org/licenses/MIT</url>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:j5ik2o/sw4jj.git</url>
        <connection>scm:git:github.com/j5ik2o/sw4jj</connection>
        <developerConnection>scm:git:git@github.com:j5ik2o/sw4jj.git</developerConnection>
      </scm>
      <developers>
        <developer>
          <id>j5ik2o</id>
          <name>Junichi Kato</name>
        </developer>
      </developers>
  },
  publishTo := sonatypePublishToBundle.value,
  credentials := {
    val ivyCredentials = (LocalRootProject / baseDirectory).value / ".credentials"
    val gpgCredentials = (LocalRootProject / baseDirectory).value / ".gpgCredentials"
    Credentials(ivyCredentials) :: Credentials(gpgCredentials) :: Nil
  }
)

lazy val baseSettings = Seq(
  organization := "com.github.j5ik2o",
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
      "-target:jvm-1.8"
    ) ++ crossScalacOptions(scalaVersion.value)),
  resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      Resolver.sonatypeRepo("releases"),
      "Seasar Repository" at "https://maven.seasar.org/maven2/"
    ),
  libraryDependencies ++= Seq(
      scalatest.scalatest % Test
    ),
  Test / fork := true,
  Test / parallelExecution := false,
  ThisBuild / scalafmtOnCompile := true
)

val `sw4jj-root` = (project in file("."))
  .settings(baseSettings, deploySettings)
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
