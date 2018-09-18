package scalaz.byNickPartridge

import scala.language.higherKinds
import scala.language.implicitConversions

trait FoldLeft4[M[_]] {
  def foldLeft[A, B](xs: M[A], b: B, f: (B, A) => B): B
}

object FoldLeft4 {
  implicit object FoldLeftList extends FoldLeft4[List]{
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }
}

trait Monoid4[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}

object Monoid4 {

  // this is like `ScalaObject with Monoid[Int]`
  implicit object IntMonoid extends Monoid4[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 + a2
    def mzero: Int = 0
  }

  // OR using implicit conversion by providing the return type
  implicit val StringMonoid: Monoid4[String] = new Monoid4[String] {
    def mappend(a1: String, a2: String): String = a1 + a2
    def mzero: String = ""
  }
}

trait Identity4[A] {
  val value: A

  def |+|(a: A)(implicit m: Monoid4[A]): A = m.mappend(value, a)
  def plus(a: A)(implicit m: Monoid4[A]): A = m.mappend(value, a)
}

object Main4 {

  implicit def toIdentity[A](a: A): Identity4[A] = new Identity4[A] {
    val value = a
  }

  val multMonoid = new Monoid4[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 * a2
    def mzero: Int = 1
  }

  def plus[T](a: T, b: T)(implicit m: Monoid4[T]): T = m.mappend(a, b)

  def sum[M[_], T](xs: M[T])(implicit m: Monoid4[T], fl: FoldLeft4[M]): T = fl.foldLeft(xs, m.mzero, m.mappend)

  def p(a: Any): Unit = { println("###> " + a) }

  def main(args: Array[String]): Unit = {
    println

    p(plus(3, 4)) // using the `plus` function
    p(5.plus(4)) // using the Identity.plus
    p(7 plus 4) // using the Identity.plus
    p(9 |+| 4) // using the Identity.plus

    p(sum(List(1,2,3,4)))
    p(sum(List("a","b","c")))
    p(sum(List(1,2,3,4))(multMonoid, implicitly[FoldLeft4[List]]))

    // signature of implicitly[FoldLeft3[List]] above
    // def implicitly[T](implicit t: T): T = t

    println
  }
}
