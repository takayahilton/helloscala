package TypeLevel

/**
 * Created by tim on 15/08/10.
 */

trait Nat

trait Zero extends Nat

trait Succ[N <: Nat] extends Nat

object Nat {
  type _0 = Zero
  type _1 = Succ[_0]
  type _2 = Succ[_1]
}


trait ToInt[N <: Nat] {
  def apply(): Int
}

object ToInt {
  def apply[N <: Nat](implicit toInt: ToInt[N]) = toInt()

  implicit def zero = new ToInt[Zero] {
    def apply() = 0
  }

  implicit def succ[N <: Nat](implicit toInt: ToInt[N]) = new ToInt[Succ[N]] {
    def apply() = toInt() + 1
  }
}

trait Add[N <: Nat, M <: Nat] {
  type Result <: Nat
}

object Add {
  type Type[N <: Nat, M <: Nat, L <: Nat] = N Add M {type Result = L}

  implicit def zero[N<:Nat] = new Add[N,Zero]{
    type Result = N
  }

  implicit def addM[N<:Nat , M<:Nat](implicit aux:Aux[N,M,Zero]) = new Add[N,Succ[M]]{
    type Result = aux.Result
  }

}

trait Aux[N<:Nat,M<:Nat,L<:Nat]{
  type Result <: Nat
}

object Aux{
  implicit def addM[N<:Nat,M<:Nat,L<:Nat](implicit aux:Aux[N,M,Succ[L]]) = new Aux[Succ[N],Succ[M],L]{
    type Result = aux.Result
  }
}





