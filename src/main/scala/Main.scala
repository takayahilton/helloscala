import MyList.MyList
import MyOption.MyOption
import TypeClasses.Monoid
import TypeClasses.MonoidInstance._
import TypeClasses.SemigroupInstance._
import TypeClassOps.OpsInstance._

trait Fuge[A]{def method:Unit}


object Main extends App{

  def foo(implicit fuge:Fuge[String])=fuge.method

  implicit val fuge = new Fuge[String]{def method=println("fuge")}

  foo


}
