package com.github.j5ik2o.sw4jj

import java.util.Date

import com.auth0.jwt.interfaces.{ Claim, Payload }

import scala.collection.JavaConverters._

class RichPayload(val underlying: Payload) extends AnyVal with PimpedType[Payload] {

  def getIssuerAsScala: Option[String] = Option(underlying.getIssuer)

  def getSubjectAsScala: Option[String] = Option(underlying.getSubject)

  def getAudienceAsScala: Seq[String] = Option(underlying.getAudience).fold(Seq.empty[String])(_.asScala)

  def getExpiresAtAsScala: Option[Date] = Option(underlying.getExpiresAt)

  def getNotBeforeAsScala: Option[Date] = Option(underlying.getNotBefore)

  def getIssuedAtAsScala: Option[Date] = Option(underlying.getIssuedAt)

  def getIdAsScala: Option[String] = Option(underlying.getId)

  def getClaimsAsScala: Map[String, Claim] = underlying.getClaims.asScala.toMap

}
