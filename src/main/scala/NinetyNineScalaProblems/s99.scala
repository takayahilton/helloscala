package NinetyNineScalaProblems

/**
 * Created by tim on 15/08/05.
 */
object workingList {

  def last[A](seq: Seq[A]): A = seq match {
    case head +: Seq() => head
    case _ +: tail => last(tail)
    case _ => throw new NoSuchElementException
  }

  def penultimate[A](seq: Seq[A]): A = seq match {
    case _ +: next +: _ +: Seq() => next
    case head +: tail => penultimate(tail)
    case _ => throw new NoSuchElementException
  }

  def nth[A](n: Int, seq: Seq[A]): A = seq match {
    case Seq() => throw new NoSuchElementException
    case head +: _ if n == 0 => head
    case _ +: tail => nth(n - 1, tail)
  }

  def length[A](seq: Seq[A]): Int = seq.foldLeft(0)((total, _) => total + 1)

  def reverse[A](seq: Seq[A]): Seq[A] = seq.foldLeft(Nil :Seq[A])((reversSeq, head) => head +: reversSeq)

  def isPalindrome[A](seq: Seq[A]): Boolean = seq == reverse(seq)

  //P08
  def compress[A](seq: Seq[A]): Seq[A] = seq match {
    case Seq() => Seq()
    case head +: tail => head +: compress(tail.dropWhile(_ == head))
  }

  //P09
  def pack[A](seq: Seq[A]): Seq[Seq[A]] = seq match {
    case Seq() => Seq()
    case l@(head +: tail) => {
      val t = l.span(_ == head)
      Seq(t._1) ++: pack(t._2)
    }
  }

  //p10
  def encode[A](seq: Seq[A]) = pack(seq).map(la => la.length -> la.head)

  //p11
  def encodeModified[A](seq: Seq[A]) = encode(seq).map {
    case (1, a) => a
    case x => x
  }

  //p12
  def decode[A](seq: Seq[(Int, A)]): Seq[A] = seq.flatMap {
    case (i, a) => Seq.fill(i)(a)
  }

  //p14
  def duplicate[A](seq: Seq[A]): Seq[A] = seq.flatMap(a => List(a, a))

  //p15
  def duplicateN[A](n: Int, seq: Seq[A]) = seq.flatMap(a => List.fill(n)(a))

  //p16
  def drop[A](n: Int, seq: Seq[A]): Seq[A] = seq match {
    case _ if n < 0 => Seq()
    case Seq() => Seq()
    case l if n == 0 => l
    case _ +: tail => drop(n - 1, tail)
  }

  //p22
  def range(n: Int, m: Int): Seq[Int] = {
    def go(total: Seq[Int]): Seq[Int] = total match {
      case _ if n > m => total
      case Seq() => go(Seq(n))
      case head +: tail if head >= m => total
      case head +: _ => go((head + 1) +: total)
    }
    go(Seq()).reverse
  }

}
