package NinetyNineScalaProblems

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader

/**
 * Created by tim on 15/08/05.
 */
object workingList {

  def last[A](list: List[A]): A = list match {
    case head :: Nil => head
    case _ :: tail => last(tail)
    case _ => throw new NoSuchElementException
  }

  def penultimate[A](list: List[A]): A = list match {
    case _ :: next :: _ :: Nil => next
    case head :: tail => penultimate(tail)
    case _ => throw new NoSuchElementException
  }

  def nth[A](n: Int, list: List[A]): A = list match {
    case Nil => throw new NoSuchElementException
    case head :: _ if n == 0 => head
    case _ :: tail => nth(n - 1, tail)
  }

  def length[A](list: List[A]): Int = list.foldLeft(0)((total, _) => total + 1)

  def reverse[A](list: List[A]): List[A] = list.foldLeft(Nil: List[A])((reversList, head) => reversList :+ head)

  def isPalindrome[A](list: List[A]): Boolean = list == list.reverse

  //P08
  def compress[A](list: List[A]): List[A] = list match {
    case Nil => Nil
    case head :: tail => head :: compress(tail.dropWhile(_ == head))
  }

  //P09
  def pack[A](list: List[A]): List[List[A]] = list match {
    case Nil => Nil
    case l@(head :: tail) => {
      val t = l.span(_ == head)
      List(t._1) ::: pack(t._2)
    }
  }

  //p10
  def encode[A](list: List[A]) = pack(list).map(la => la.length -> la.head)

  //p11
  def encodeModified[A](list: List[A]) = encode(list).map {
    case (1, a) => a
    case x => x
  }

  //p12
  def decode[A](list: List[(Int, A)]): List[A] = list.map {
    case (i, a) => List.fill(i)(a)
  }.flatten

  //p14
  def duplicate[A](list: List[A]): List[A] = list.flatMap(a => List(a, a))

  //p15
  def duplicateN[A](n: Int, list: List[A]) = list.flatMap(a => List.fill(n)(a))

  //p16
  def drop[A](n: Int, list: List[A]): List[A] = list match {
    case _ if n < 0 => Nil
    case Nil => Nil
    case l if n == 0 => l
    case _ :: tail => drop(n - 1, tail)
  }

  //p22
  def range(n: Int, m: Int): List[Int] = {
    def go(total: List[Int]): List[Int] = total match {
      case _ if n > m => total
      case Nil => go(List(n))
      case head::tail if head >= m => total
      case head::_ => go((head+1)::total)
    }
    go(Nil).reverse
  }

}
