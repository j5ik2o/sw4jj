package com.github.j5ik2o.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.j5ik2o.sw4jj.Implicits._
import org.scalatest.{ FunSpec, Matchers }

class JWTSpec extends FunSpec with Matchers {

  describe("JWT") {

    it("should call methods for Scala") {
      val token = JWT.create()
        .withIssuer("auth0")
        .withArrayClaim("test", Array("test-1"))
        .sign(Algorithm.HMAC256("secret"))

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

    }

  }

}
