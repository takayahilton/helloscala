package TypeLevel

import scala.language.higherKinds

/**
 * Created by tim on 15/08/10.
 */

trait Fold[-Elem, Value] {
  type Apply[E <: Elem, V <: Value] <: Value
}

sealed trait Nat {
  type Match[NonZero[N <: Nat] <: Up, IfZero <: Up, Up] <: Up
  type FoldR[Init <: Type, Type, F <: Fold[Nat, Type]] <: Type
}

sealed trait Zero extends Nat {
  type Match[NonZero[N <: Nat] <: Up, IfZero <: Up, Up] = IfZero
}

sealed trait Succ[N <: Nat] extends Nat {
  type Match[NonZero[N <: Nat] <: Up, IfZero <: Up, Up] = IfZero
}

