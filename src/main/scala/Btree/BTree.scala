package Btree

import scala.math.{Numeric, Ordering}
import Ordering.Implicits._
import Numeric.Implicits._

/**
 * Created by tim on 15/08/05.
 * https://gist.github.com/j5ik2o/7332812
 * の問題
 */
sealed trait Node[+A]

case class Leaf[+A](value: A) extends Node[A]

case class Branch[+A](left: Node[A], value: A, right: Node[A]) extends Node[A]

case class BTree[A](node: Node[A])(implicit ordering: Ordering[A], numeric: Numeric[A]) {
  def size: Int = node match {
    case Leaf(_) => 1
    case Branch(l, _, r) => BTree(l).size + 1 + BTree(r).size
  }

  def max: (A) = node match {
    case Leaf(v) => v
    case Branch(l, v, r) => ordering.max(v, BTree(r).max)
  }

  def min: A = node match {
    case Leaf(v) => v
    case Branch(l, v, r) => ordering.min(BTree(l).min, v)
  }

  def sum: A = node match {
    case Leaf(v) => v
    case Branch(l, v, r) => BTree(l).sum + v + BTree(r).sum
  }

  def avg: Double = numeric.toDouble(sum) / size

}