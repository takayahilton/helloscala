import MyList.MyList
import TypeClasses.Monoid
import TypeClasses.MonoidInstance._
import TypeClassOps.OpsInstance._

/**
 * Created by tim on 15/08/07.
 */
object Main extends App{

  val tes = MyList(1,2,3) |+| MyList(1,2,3)

  println(s"${tes}")

}
