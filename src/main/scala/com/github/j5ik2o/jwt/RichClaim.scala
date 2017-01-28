package com.github.j5ik2o.jwt

import java.util.Date

import com.auth0.jwt.interfaces.Claim

import scala.collection.JavaConverters._
import scala.reflect.ClassTag

object RichClaim {

  trait Converter[A] {

    def convert(self: Claim): A

  }

  implicit val intConverter = new Converter[Int] {
    override def convert(self: Claim) =
      self.asInt()
  }

  implicit val booleanConverter = new Converter[Boolean] {
    override def convert(self: Claim) =
      self.asBoolean()
  }

  implicit val stringConverter = new Converter[String] {
    override def convert(self: Claim) =
      self.asString()
  }

  implicit val doubleConveter = new Converter[Double] {
    override def convert(self: Claim) =
      self.asDouble()
  }

  implicit val dateConverter = new Converter[Date] {
    override def convert(self: Claim) =
      self.asDate()
  }

  implicit def optConverter[T](implicit c: Converter[T]) = new Converter[Option[T]] {
    override def convert(self: Claim): Option[T] =
      Option(c.convert(self))
  }

  implicit def arrayConverter[T: ClassTag] = new Converter[Array[T]] {
    override def convert(self: Claim): Array[T] = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      Option(self.asArray(ru)).map(_.asInstanceOf[Array[T]]).getOrElse(Array.empty[T])
    }
  }

  implicit def setConverter[T: ClassTag] = new Converter[Set[T]] {
    override def convert(self: Claim): Set[T] = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      Option(self.asList(ru)).map(_.asScala.asInstanceOf[Set[T]]).getOrElse(Set.empty[T])
    }
  }

  implicit def seqConverter[T: ClassTag] = new Converter[Seq[T]] {
    override def convert(self: Claim): Seq[T] = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      Option(self.asList(ru)).map(_.asScala.asInstanceOf[Seq[T]]).getOrElse(Seq.empty[T])
    }
  }

  implicit def listConverter[T: ClassTag] = new Converter[List[T]] {
    override def convert(self: Claim): List[T] = {
      val ru = implicitly[ClassTag[T]].runtimeClass
      Option(self.asList(ru)).map(_.asScala.asInstanceOf[List[T]]).getOrElse(List.empty[T])
    }
  }

  implicit def otherConverter[T: ClassTag] = new Converter[T] {
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
