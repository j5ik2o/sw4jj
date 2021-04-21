# sw4jj

[![Actions Status: CI](https://github.com/j5ik2o/sw4jj/workflows/CI/badge.svg)](https://github.com/j5ik2o/sw4jj/actions?query=workflow%3A"CI")
[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.j5ik2o/sw4jj_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.j5ik2o/sw4jj_2.13)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fj5ik2o%2Fsw4jj.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fj5ik2o%2Fsw4jj?ref=badge_shield)

Simple scala Wrapper For [Java-Jwt](https://github.com/auth0/java-jwt) is sw4jj.

## Installation

Add the following to your sbt build (Scala 2.11.x, 2.12.x, 2.13.x):

### Release Version

```scala
libraryDependencies += "com.github.j5ik2o" %% "sw4jj" % "(version)"
```

### Snapshot Version

```scala
resolvers += "Sonatype OSS Snapshot Repository" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "com.github.j5ik2o" %% "sw4jj" % "(version)"
```

## Usage

Please import `com.github.j5ik2o.sw4jj.Implicits._`.
You can Scala methods added to java-jwt (Header, Playload, Claim).

```scala
import com.auth0.jwt.JWT
import com.github.j5ik2o.sw4jj.Implicits._

val verifier = JWT.require(Algorithm.HMAC256("secret"))
  .withIssuer("auth0")
  .withArrayClaim("test", "test-1")
  .build()

val jwt = verifier.verify(token)

// for RichHeader
jwt.getAlgorithmAsScala should contain("HS256")
jwt.getTypeAsScala shouldBe None
jwt.getContentTypeAsScala shouldBe None
jwt.getKeyIdAsScala shouldBe None

// for RichPayload
jwt.getSubjectAsScala shouldBe None
jwt.getAudienceAsScala shouldBe Seq.empty
jwt.getExpiresAtAsScala shouldBe None
jwt.getNotBeforeAsScala shouldBe None
jwt.getIssuedAtAsScala shouldBe None
jwt.getIdAsScala shouldBe None

// for RichClaim
jwt.getClaim("iss").asScala[Option[String]] should contain("auth0")
jwt.getClaimsAsScala.get("test").map(_.asScala[Array[String]]) should contain(Array("test-1"))

```


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fj5ik2o%2Fsw4jj.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fj5ik2o%2Fsw4jj?ref=badge_large)
