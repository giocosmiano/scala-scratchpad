package tailRec

import com.typesafe.scalalogging.StrictLogging

import scala.annotation.tailrec

//
// Martin Odersky's "Programming in Scala 2nd edition",
// https://stackoverflow.com/questions/12496959/summing-values-in-a-list
object Gcd extends App with StrictLogging {

  val a = 14
  val b = 21

  println(s"gcd $a, $b")
  println(s"gcd == ${gcd(a, b)}")

  def gcd(a: Int, b: Int): Int = {
    @tailrec
    def gcdHelper(a: Int, b: Int): Int = {
      if (b == 0) a else gcdHelper(b, a % b)
    }
    gcdHelper(a, b)
  }
}
