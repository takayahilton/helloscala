package MyList

import MyOption._

/**
 * Created by tim on 15/08/07.
 */
sealed trait MyList[+A] {
  self =>

  case class WithFilter[C >: A](f: C => Boolean) {

    def flatMap[B](g: C => MyList[B]): MyList[B] = self.foldRight(MyNil: MyList[B]) {
      case (a, b) if f(a) => g(a) ++ b
      case (_, b) => b
    }

    def map[B](g: C => B) = flatMap(x => MyList(g(x)))
  }

  def foldLeft[B](z: B)(f: (B, A) => B): B = this match {
    case MyNil => z
    case MyCons(head, tail) => tail.foldLeft(f(z, head))(f)
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case MyNil => z
    case MyCons(head, tail) => f(head, tail.foldRight(z)(f))
  }

  def length = foldLeft(0)((b, a) => b + 1)

  def ::[B >: A](b: B): MyList[B] = MyCons(b, this)

  def reverse: MyList[A] = foldLeft(MyNil: MyList[A])((b, a) => a :: b)

  def ++[B >: A](b: MyList[B]): MyList[B] = foldRight(b)((a, b) => a :: b)

  def flatMap[B](f: A => MyList[B]): MyList[B] = foldRight(MyNil: MyList[B])((a, b) => f(a) ++ b)

  def map[B](f: A => B): MyList[B] = flatMap(x => MyList(f(x)))

  def filter(f: A => Boolean): MyList[A] = foldRight(MyNil: MyList[A]) {
    case (a, b) if f(a) => a :: b
    case (_, b) => b
  }

  def withFilter[B >: A](f: B => Boolean) = WithFilter(f)

  def flatten[B](implicit A: A <:< MyList[B]) = flatMap(f => f)

  //foldRightを使って全走査しないバージョン
  def find(f: A => Boolean): MyOption[A] = {
    def g(a: A, b:  =>MyOption[A]) = {
      println(a); if (f(a)) MyOption(a) else b
    }
    foldRight(MyNone: MyOption[A])(g)
  }


}

case class MyCons[+A](value: A, next: MyList[A]) extends MyList[A]

case object MyNil extends MyList[Nothing]


object MyList {

  def empty[A]: MyList[A] = MyNil

  def apply[A](as: A*): MyList[A] = as.foldRight(MyNil: MyList[A])((last, total) => MyCons(last, total))

}