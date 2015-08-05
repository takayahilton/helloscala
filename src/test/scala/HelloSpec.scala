
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Gen.choose
import example.Answers

import scala.util.{Success, Failure}

class exampleTest extends Properties("list"){

  property("getSum") = forAll{ (list:List[Int]) =>
    Answers.getSum(list) == list.sum
  }

  property("qsort") = forAll{list:List[Int]=>
    Answers.qsort(list) == list.sorted
  }

}