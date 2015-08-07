package TypeClassOps

import MyList.MyList
import TypeClasses.Monoid
/**
 * Created by tim on 15/08/07.
 */

object MonoidOps{

  implicit class toMonoidOp[A:Monoid](a:A) {
     def |+|(a2:A) = implicitly[Monoid[A]].append(a,a2)
  }

}
