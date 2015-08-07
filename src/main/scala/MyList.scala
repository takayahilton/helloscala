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

    def map[B](g: C => B) = this.flatMap(x => MyList(g(x)))
  }

  def foldLeft[B](z: B)(f: (B, A) => B): B = this match {
    case MyNil => z
    case MyCons(head, tail) => tail.foldLeft(f(z, head))(f)
  }

  def foldRight[B](z: B)(f: (A, B) => B): B = (this.foldLeft((x: B) => x)((g, a) => b => g(f(a, b))))(z)

  def length = this.foldLeft(0)((b,a)=>b+1)

  def ::[B >: A](b: B): MyList[B] = MyCons(b, this)

  def reverse: MyList[A] = this.foldLeft(MyNil: MyList[A])((b, a) => a :: b)

  def ++[B >: A](b: MyList[B]): MyList[B] = this.foldRight(b)((a, b) => a :: b)

  def flatMap[B](f: A => MyList[B]): MyList[B] = this.foldRight(MyNil: MyList[B])((a, b) => f(a) ++ b)

  def map[B](f: A => B): MyList[B] = this.flatMap(x => MyList(f(x)))

  def filter(f: A => Boolean): MyList[A] = foldRight(MyNil: MyList[A]) {
    case (a, b) if f(a) => a :: b
    case (_, b) => b
  }

  def withFilter[B >: A](f: B => Boolean) = WithFilter(f)

  def find(f: A => Boolean): MyOption[A] = this.foldLeft(MyNone: MyOption[A]) {
    case (b@MySome(_), _) => b
    case (b, a) if f(a) => MySome(a)
    case _ => MyNone
  }

}

case class MyCons[+A](value: A, next: MyList[A]) extends MyList[A]

case object MyNil extends MyList[Nothing]


object MyList {

  def empty[A]: MyList[A] = MyNil

  def apply[A](as: A*): MyList[A] = as.foldRight(MyNil: MyList[A])((last, total) => MyCons(last, total))

}