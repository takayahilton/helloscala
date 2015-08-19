package MyOption

import java.util.NoSuchElementException

/**
 * Created by tim on 15/08/07.
 */

sealed trait MyOption[+A] {

  def get: A = this match {
    case MySome(v) => v
    case MyNone => throw new NoSuchElementException
  }

  def getOrElse[B >: A](elseValue: B): B = this match {
    case MySome(v) => v
    case MyNone => elseValue
  }

  def isEmpty: Boolean = this match {
    case MySome(_) => false
    case MyNone => true
  }

  def map[B](f: A => B): MyOption[B] = this match {
    case MySome(a) => MySome(f(a))
    case MyNone => MyNone
  }

  def flatMap[B](f: A => MyOption[B]): MyOption[B] = this match {
    case MySome(a) => f(a)
    case MyNone => MyNone
  }

  def filter(f: A => Boolean): MyOption[A] = this match {
    case MyNone => MyNone
    case MySome(a) if f(a) => MySome(a)
    case _ => MyNone
  }

  def orElse[B >: A](elseValue: => MyOption[B]): MyOption[B] = this match {
    case MyNone => elseValue
    case x => x
  }

  def flatten[B](implicit A: A <:< MyOption[B]) = flatMap(a => a)

}

case class MySome[+A](value: A) extends MyOption[A]

case object MyNone extends MyOption[Nothing]

object MyOption {
  def apply[A](x: A): MyOption[A] = {
    if (x == null) {
      MyNone
    } else {
      MySome(x)
    }
  }
}
