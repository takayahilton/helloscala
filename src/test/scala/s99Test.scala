import org.scalacheck.Properties
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

  property("reverse") = forAll { list: List[String] =>
    workingList.reverse(list) == workingList.reverse(workingList.reverse(list))
  }

  property("compress") = forAll { list: List[Int] =>
    workingList.compress(list) == workingList.compress(workingList.compress(list))
  }

  property("range") = forAll(choose(-1000, 1000), choose(-1000, 1000)) { (n: Int, m: Int) =>
    workingList.range(n, m).length == {
      if (n <= m) m - n + 1 else 0
    }
  }


}
