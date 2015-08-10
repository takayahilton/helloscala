package TypeClassOps

import MyList.MyList
import TypeClasses.{Functor, Monoid}
import scala.language.higherKinds

/**
 * Created by tim on 15/08/07.
 */

object OpsInstance {

  implicit class toMonoidOp[A: Monoid](a1: A) {
    def |+|(a2: A) = implicitly[Monoid[A]].append(a1, a2)
  }

  implicit class toFunctorOp[F[_] : Functor, A](fa: F[A]) {

    def map[B](f: A => B): F[B] = implicitly[Functor[F]].map(fa)(f)

  }


}
