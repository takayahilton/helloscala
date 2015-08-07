package TypeClasses

import MyList._
import MyOption.{MySome, MyNone, MyOption}

/**
 * Created by tim on 15/08/07.
 */
trait Monoid[F] extends Semigroup[F] {

  def zero: F

}


object MonoidInstance {


  implicit def MyListMonoid[F] = new Monoid[MyList[F]] {

    def zero = MyNil

    def append(f1: MyList[F], f2: => MyList[F]) = f1 ++ f2

  }


  implicit def MyOptionMonoid[A:Semigroup]:Monoid[MyOption[A]] = new Monoid[MyOption[A]] {

    def append(f1: MyOption[A], f2: =>MyOption[A]) = (f1, f2) match {
      case (MySome(a), MySome(b)) => MySome(implicitly[Semigroup[A]].append(a,b))
      case (MySome(_), MyNone) => f1
      case (MyNone, MySome(_)) => f2
      case (MyNone, MyNone) => MyNone
    }

    def zero = MyNone

  }



}
