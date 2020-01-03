
val coreSettings = Seq(
  sonatypeProfileName := "com.github.j5ik2o",
  organization := "com.github.j5ik2o",
  scalaVersion := "2.11.8",
  crossScalaVersions := Seq("2.10.5", "2.11.8", "2.12.0"),
  scalacOptions ++= Seq(
    "-feature"
    , "-deprecation"
    , "-unchecked"
    , "-encoding"
    , "UTF-8"
    , "-Xfatal-warnings"
    , "-language:existentials"
    , "-language:implicitConversions"
    , "-language:postfixOps"
    , "-language:higherKinds"
    , "-Ywarn-adapted-args" // Warn if an argument list is modified to match the receiver
    , "-Ywarn-dead-code" // Warn when dead code is identified.
    , "-Ywarn-inaccessible" // Warn about inaccessible types in method signatures.
    , "-Ywarn-nullary-override" // Warn when non-nullary `def f()' overrides nullary `def f'
    , "-Ywarn-nullary-unit" // Warn when nullary methods return Unit.
    , "-Ywarn-numeric-widen" // Warn when numerics are widened.
    , "-Xmax-classfile-name", "200"
  ),
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := {
    _ => false
  },
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
  credentials := Def.task {
    val ivyCredentials = (baseDirectory in LocalRootProject).value / ".credentials"
    val result = Credentials(ivyCredentials) :: Nil
    result
  }.value
) ++ scalariformSettings

val root = (project in file("."))
  .settings(
    name := "sw4jj"
  ).settings(coreSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.auth0" % "java-jwt" % "3.9.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )

