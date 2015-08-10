package TypeClasses

/**
 * Created by tim on 15/08/10.
 */
trait Foldable[F[_]] {

  def foldMap[A, B: Monoid](fa: F[A])(f: A => B): B

  def foldRight[A, B](fa: F[A], z: => B)(f: (A, => B) => B): B

}


