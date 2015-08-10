package TypeClasses

import scala.language.higherKinds
import MyList.MyList

/**
 * Created by tim on 15/08/07.
 */
trait Functor[F[_]] {
  def map[A,B](fa:F[A])(f:A=>B):F[B]
}

object FunctorInstance{

  implicit val MyListFunctor = new Functor[MyList] {
    def map[A,B](fa:MyList[A])(f:A=>B):MyList[B] = fa.map(f)
  }

}