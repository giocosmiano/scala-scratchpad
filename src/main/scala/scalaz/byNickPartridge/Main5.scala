package scalaz.byNickPartridge

import scala.language.higherKinds
import scala.language.implicitConversions

trait FoldLeft5[M[_]] {
  def foldLeft[A, B](xs: M[A], b: B, f: (B, A) => B): B
}

object FoldLeft5 {
  implicit object FoldLeftList extends FoldLeft5[List]{
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }
}

trait Monoid5[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}

object Monoid5 {

  // this is like `ScalaObject with Monoid[Int]`
  implicit object IntMonoid extends Monoid5[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 + a2
    def mzero: Int = 0
  }

  // OR using implicit conversion by providing the return type
  implicit val StringMonoid: Monoid5[String] = new Monoid5[String] {
    def mappend(a1: String, a2: String): String = a1 + a2
    def mzero: String = ""
  }
}

trait Identity5[A] {
  val value: A

  def |+|(a: A)(implicit m: Monoid5[A]): A = m.mappend(value, a)
  def plus(a: A)(implicit m: Monoid5[A]): A = m.mappend(value, a)
}

trait MA[M[_], A] {
  val value: M[A]

  def sumMA(implicit m: Monoid5[A], fl: FoldLeft5[M]): A = fl.foldLeft(value, m.mzero, m.mappend)
}

object Main5 {

  implicit def toIdentity[A](a: A): Identity5[A] = new Identity5[A] {
    val value = a
  }

  implicit def toMA[M[_], A](ma: M[A]): MA[M, A] = new MA[M, A] {
    val value = ma
  }

  val multMonoid = new Monoid5[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 * a2
    def mzero: Int = 1
  }

  def plus[T](a: T, b: T)(implicit m: Monoid5[T]): T = m.mappend(a, b)

  def sum[M[_], T](xs: M[T])(implicit m: Monoid5[T], fl: FoldLeft5[M]): T = fl.foldLeft(xs, m.mzero, m.mappend)

  def p(a: Any): Unit = { println("###> " + a) }

  def main(args: Array[String]): Unit = {
    println

    p(plus(3, 4)) // using the `plus` function
    p(5.plus(4)) // using the Identity.plus
    p(7 plus 4) // using the Identity.plus
    p(9 |+| 4) // using the Identity.plus

    println

    p(List(1,2,3,4,5).sumMA) // using MA.sumMA
    p(List("1","2","3","4","5").sumMA) // using MA.sumMA


    println

    p(sum(List(1,2,3,4)))
    p(sum(List("a","b","c")))
    p(sum(List(1,2,3,4))(multMonoid, implicitly[FoldLeft5[List]]))

    // signature of implicitly[FoldLeft5[List]] above
    // def implicitly[T](implicit t: T): T = t

    println
  }
}
