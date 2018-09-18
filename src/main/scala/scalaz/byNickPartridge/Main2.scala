package scalaz.byNickPartridge

import scala.language.higherKinds
import scala.language.implicitConversions

trait Monoid2[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}

object Monoid2 {

  // this is like `ScalaObject with Monoid[Int]`
  implicit object IntMonoid extends Monoid2[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 + a2
    def mzero: Int = 0
  }

  // OR using implicit conversion by providing the return type
  implicit val StringMonoid: Monoid2[String] = new Monoid2[String] {
    def mappend(a1: String, a2: String): String = a1 + a2
    def mzero: String = ""
  }
}

object Main2 {
  val multMonoid = new Monoid2[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 * a2
    def mzero: Int = 1
  }

  def sum[T](xs: List[T])(implicit m: Monoid2[T]): T = xs.foldLeft(m.mzero)(m.mappend)

  def p(a: Any): Unit = { println("###> " + a) }

  def main(args: Array[String]): Unit = {
    println

    p(sum(List(1,2,3,4)))
    p(sum(List("a","b","c")))
    p(sum(List(1,2,3,4))(multMonoid))

    println
  }
}
