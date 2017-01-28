package com.github.j5ik2o.jwt

import com.auth0.jwt.interfaces.{ Claim, Header }

class RichHeader(val underlying: Header) extends AnyVal with PimpedType[Header] {

  def getAlgorithmAsScala: Option[String] = Option(underlying.getAlgorithm)

  def getTypeAsScala: Option[String] = Option(underlying.getType)

  def getContentTypeAsScala: Option[String] = Option(underlying.getContentType)

  def getKeyIdAsScala: Option[String] = Option(underlying.getKeyId)

}
