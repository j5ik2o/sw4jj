package com.github.j5ik2o.jwt

trait PimpedType[T] extends Any {

  def underlying: T

}
