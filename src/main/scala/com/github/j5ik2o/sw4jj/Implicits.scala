package com.github.j5ik2o.sw4jj

import com.auth0.jwt.interfaces.{ Claim, Header, Payload }

trait Implicits {

  implicit def richPayload(underlying: Payload): RichPayload = new RichPayload(underlying)

  implicit def richHeader(underlying: Header): RichHeader = new RichHeader(underlying)

  implicit def richClaim(underlying: Claim): RichClaim = new RichClaim(underlying)

}

object Implicits extends Implicits
