package example

import scala.util.{Failure, Try, Success}

/**
 * Created by tim on 15/08/05.
 * https://gist.github.com/j5ik2o/7264785 と
 * https://gist.github.com/j5ik2o/7210762 の問題
 */
object Answers {

  import scala.annotation.tailrec

  def binarySearch(number: Int, numbers: List[Int]): Try[Int] = {
    @tailrec
    def go(left: Int, right: Int): Try[Int] = {
      if (left < right) {
        val mid = (left + right) / 2
        if (numbers(mid) < number) {
          go(mid + 1, right)
        } else {
          go(left, mid)
        }
      } else {
        if (number == numbers(left)) {
          Success(left)
        } else {
          Failure(new NoSuchElementException)
        }
      }
    }

    numbers match{
      case Nil=>Failure(new NoSuchElementException)
      case _ =>go(0, numbers.length - 1)
    }
  }

  def qsort(numbers: List[Int]): List[Int] = numbers match {
    case _ if numbers.length <= 1 => numbers
    case _ => {
      val mid = numbers((numbers.length - 1) / 2)
      qsort(numbers.filter(_ < mid)) ::: numbers.filter(_ == mid) ::: qsort(numbers.filter(_ > mid))
    }
  }


  def countFruitsFromLines(linens: List[String]): Map[String, Int] = {
    val fruits = linens.map(_.split(" ")).flatten
    fruits.map(x => x -> fruits.count(_ == x)).distinct.toMap
  }

  def fib(n: Long): Long = n match {
    case 0 => 0
    case 1 => 1
    case n => fib(n - 1) + fib(n - 2)
  }

  def fact(n: Int): Long = {
    @tailrec
    def go(n: Int, total: Long): Long = n match {
      case 1 => total
      case n => go(n - 1, n * total)
    }
    go(n, 1)
  }

  def getSum(numbers: List[Int]): Long = numbers.foldLeft(0)(_ + _)


}
