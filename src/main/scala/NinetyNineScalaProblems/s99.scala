package NinetyNineScalaProblems

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
    case head :: tail => head :: compress(tail.dropWhile(_ != head))
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

}
