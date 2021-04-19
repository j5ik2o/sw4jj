package com.github.j5ik2o.sw4jj

import com.auth0.jwt.interfaces.Claim

import java.util.Date
import scala.jdk.CollectionConverters._
import scala.reflect.ClassTag

object RichClaim {

  trait Converter[A] {

    def convert(self: Claim): A

  }

  implicit val intConverter: Converter[Int] = new Converter[Int] {

    override def convert(self: Claim) =
      self.asInt()
  }

  implicit val booleanConverter: Converter[Boolean] = new Converter[Boolean] {

    override def convert(self: Claim) =
      self.asBoolean()
  }

  implicit val stringConverter: Converter[String] = new Converter[String] {

    override def convert(self: Claim) =
      self.asString()
  }

  implicit val doubleConveter: Converter[Double] = new Converter[Double] {

    override def convert(self: Claim) =
      self.asDouble()
  }

  implicit val dateConverter: Converter[Date] = new Converter[Date] {

    override def convert(self: Claim) =
      self.asDate()
  }

  implicit def optConverter[T](implicit c: Converter[T]): Converter[Option[T]] = new Converter[Option[T]] {

    override def convert(self: Claim): Option[T] =
      Option(c.convert(self))
  }

  implicit def arrayConverter[T: ClassTag]: Converter[Array[T]] = new Converter[Array[T]] {

    override def convert(self: Claim): Array[T] = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      Option(self.asArray(ru)).map(_.asInstanceOf[Array[T]]).getOrElse(Array.empty[T])
    }
  }

  implicit def setConverter[T: ClassTag](): Converter[Set[T]] = new Converter[Set[T]] {

    override def convert(self: Claim): Set[T] = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      Option(self.asList(ru)).map(_.asScala.asInstanceOf[Set[T]]).getOrElse(Set.empty[T])
    }
  }

  implicit def seqConverter[T: ClassTag]: Converter[Seq[T]] = new Converter[Seq[T]] {

    override def convert(self: Claim): Seq[T] = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      Option(self.asList(ru)).map(_.asScala.asInstanceOf[Seq[T]]).getOrElse(Seq.empty[T])
    }
  }

  implicit def listConverter[T: ClassTag]: Converter[List[T]] = new Converter[List[T]] {

    override def convert(self: Claim): List[T] = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      Option(self.asList(ru)).map(_.asScala.asInstanceOf[List[T]]).getOrElse(List.empty[T])
    }
  }

  implicit def otherConverter[T: ClassTag]: Converter[T] = new Converter[T] {

    override def convert(self: Claim): T = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      self.as(ru).asInstanceOf[T]
    }
  }

}

class RichClaim(val underlying: Claim) extends AnyVal with PimpedType[Claim] {

  import RichClaim._

  def asScala[A](implicit c: Converter[A]): A = {
    c.convert(underlying)
  }

}
