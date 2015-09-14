package macroHello

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros


trait Macro{
  def helloMethod = macro Macro.impl
}


object Macro {

  def impl(c: Context): c.Expr[String] = {
    import c.universe._
    c.Expr[String](q"${c.prefix.tree}.hello")
  }
}