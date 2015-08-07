package Btree

import scala.math.{Numeric, Ordering}
import Ordering.Implicits._
import Numeric.Implicits._

/**
 * Created by tim on 15/08/05.
 * https://gist.github.com/j5ik2o/7332812
 * の問題
 */
sealed trait Node[+A] {
  val value: A
  val size: Int
  val sum: A
  val avg: Double
  val max: A
  val min: A

  def find[B >: A](value: B): Option[Node[B]]
}

case class Leaf[+A: Ordering : Numeric](value: A) extends Node[A] {

  val size = 1
  val sum = value
  val avg = value.toDouble
  val max = value
  val min = value

  def find[B >: A](value: B) = if (value == this.value) Some(this) else None
}

case class Branch[+A: Ordering : Numeric](left: Node[A], value: A, right: Node[A]) extends Node[A] {

  require(left.value < value)
  require(value < right.value)

  val size = left.size + 1 + right.size
  val sum = left.sum + value + right.sum
  val avg = sum.toDouble / size
  val max = right.value
  val min = left.value

  def find[B >: A](value: B) = this match {
    case Branch(_, v, _) if v == value => Some(this)
    case Branch(l, v, _) if value.asInstanceOf[A] < v => l.find(value)
    case Branch(_, v, r) if value.asInstanceOf[A] > v => r.find(value)
  }

}

case class BTree[+A](node: Node[A]) {

  lazy val size: Int = node.size

  lazy val sum: A = node.sum

  lazy val avg: Double = node.avg

  lazy val max: A = node.max

  lazy val min: A = node.min

  def find[B >: A](value: B): Option[Node[B]] = node.find(value)

}

object BTree {

  def from[A: Ordering : Numeric](values: Seq[A]): Node[A] = {
    if (values.size == 1) {
      Leaf(values.head)
    } else {
      require(values.size % 2 == 1)
      val (left, mid +: right) = values.splitAt(values.size / 2)
      Branch(from(left), mid, from(right))
    }
  }

  def apply[A: Ordering : Numeric](values:A*): BTree[A] = {
    BTree(from(values))
  }

}