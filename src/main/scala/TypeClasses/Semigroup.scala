package TypeClasses

/**
 * Created by tim on 15/08/07.
 */
trait Semigroup[F] {
  def append(f1:F,f2: =>F):F
}


object SemigroupInstance{

  implicit def IntSemigroup = new Semigroup[Int] {
    def append(f1:Int,f2: =>Int)= f1 + f2
  }




}