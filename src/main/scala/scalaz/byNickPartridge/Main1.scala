package scalaz.byNickPartridge

import scala.language.higherKinds

trait Monoid1[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}

object IntMonoid extends Monoid1[Int] {
  def mappend(a1: Int, a2: Int): Int = a1 + a2
  def mzero: Int = 0
}

object Main1 {

  implicit val intMonoid = IntMonoid

  def sum[T](xs: List[T])(implicit m: Monoid1[T]): T = xs.foldLeft(m.mzero)(m.mappend)

  def p(a: Any): Unit = { println("###> " + a) }

  def main(args: Array[String]): Unit = {
    println

    p(sum(List(1,2,3,4)))

    println
  }
}
