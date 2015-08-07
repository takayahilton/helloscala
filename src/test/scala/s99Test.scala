import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll
import org.scalacheck.Gen.choose
import NinetyNineScalaProblems.workingList

/**
 * Created by tim on 15/08/05.
 */

class s99Test extends Properties("s99") {

  property("length") = forAll { (xs: List[Int], ys: List[Int]) =>
    workingList.length(xs) + workingList.length(ys) == workingList.length(xs ::: ys)
  }

  property("reverse") = forAll { list: List[Int] =>
    workingList.reverse(workingList.reverse(list)) == list
  }

  property("compress") = forAll { list: List[Int] =>
    workingList.compress(list) == workingList.compress(workingList.compress(list))
  }

  property("pack") = forAll { list: List[Int] =>
    workingList.pack(list).flatten == list
  }

  property("encode and decode") = forAll { list: List[Int] =>
    workingList.decode(workingList.encode(list)) == list
  }

  property("duplicate") = forAll { list: List[Int] =>
    workingList.duplicate(list).length == list.length * 2
  }


  property("duplicateN") = forAll(choose(0, 100), intList) { (n: Int, list: List[Int]) =>
    workingList.duplicateN(n, list).length == list.length * n
  }

  property("range") = forAll(choose(-1000, 1000), choose(-1000, 1000)) { (n: Int, m: Int) =>
    workingList.range(n, m).length == {
      if (n <= m) m - n + 1 else 0
    }
  }


}
