# sw4jj


[![CircleCI](https://circleci.com/gh/j5ik2o/sw4jj/tree/main.svg?style=shield&circle-token=9f6f53d09f0fb87ee8ea81246e69683d668291cd)](https://circleci.com/gh/j5ik2o/sw4jj/tree/main)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.j5ik2o/sw4jj_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.j5ik2o/sw4jj_2.13)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

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
