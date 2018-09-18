package scalaz.byNickPartridge

import scala.language.higherKinds
import scala.language.implicitConversions

trait FoldLeft3[M[_]] {
  def foldLeft[A, B](xs: M[A], b: B, f: (B, A) => B): B
}

object FoldLeft3 {
  implicit object FoldLeftList extends FoldLeft3[List]{
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }
}

trait Monoid3[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}

object Monoid3 {

  // this is like `ScalaObject with Monoid[Int]`
  implicit object IntMonoid extends Monoid3[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 + a2
    def mzero: Int = 0
  }

  // OR using implicit conversion by providing the return type
  implicit val StringMonoid: Monoid3[String] = new Monoid3[String] {
    def mappend(a1: String, a2: String): String = a1 + a2
    def mzero: String = ""
  }
}

object Main3 {

  val multMonoid = new Monoid3[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 * a2
    def mzero: Int = 1
  }

  def sum[M[_], T](xs: M[T])(implicit m: Monoid3[T], fl: FoldLeft3[M]): T = fl.foldLeft(xs, m.mzero, m.mappend)

  def p(a: Any): Unit = { println("###> " + a) }

  def main(args: Array[String]): Unit = {
    println

    p(sum(List(1,2,3,4)))
    p(sum(List("a","b","c")))
    p(sum(List(1,2,3,4))(multMonoid, implicitly[FoldLeft3[List]]))

    // signature of implicitly[FoldLeft3[List]] above
    // def implicitly[T](implicit t: T): T = t

    println
  }
}
