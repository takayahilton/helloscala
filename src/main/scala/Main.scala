import MyList.MyList
import MyOption.MyOption
import TypeClasses.Monoid
import TypeClasses.MonoidInstance._
import TypeClasses.SemigroupInstance._
import TypeClassOps.OpsInstance._

/**
 * Created by tim on 15/08/07.
 */
object Main extends App{

  val tes = MyList(1,2,3) |+| MyList(1,2,3)

  val tes2 = MyOption(1) |+|  MyOption(2)

  println(s"${tes2}")

}
