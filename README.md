# sw4jj

Simple scala Wrapper For Java-Jwt is sw4jj


## Installation

Add the following to your sbt build (Scala 2.10.x, 2.11.x, 2.12.x):

### Release Version

```scala
resolvers += "Sonatype OSS Release Repository" at "https://oss.sonatype.org/content/repositories/releases/"

libraryDependencies += "com.github.j5ik2o" %% "sw4jj" % "1.0.1"
```

### Snapshot Version

```scala
resolvers += "Sonatype OSS Snapshot Repository" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "com.github.j5ik2o" %% "sw4jj" % "1.0.2-SNAPSHOT"
```

## Usage

Please import `com.github.j5ik2o.jwt.Implicits._`.
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
