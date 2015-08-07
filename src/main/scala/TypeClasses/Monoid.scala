package TypeClasses

import MyList._

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

}
