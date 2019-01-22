package tailRec

import com.typesafe.scalalogging.StrictLogging

import scala.annotation.tailrec

// https://alvinalexander.com/scala/scala-recursion-examples-recursive-programming
//
// Martin Odersky's "Programming in Scala 2nd edition",
// https://stackoverflow.com/questions/12496959/summing-values-in-a-list
object Fibonacci extends App with StrictLogging {

  val x = 100
  println(s"fibonacci of $x")
  println(s"fibonacci == ${fib(x)}")

  def fib(x: Int): BigInt = {
    @tailrec def fibHelper(x: Int, prev: BigInt = 0, next: BigInt = 1): BigInt = x match {
      case 0 => prev
      case 1 => next
      case _ => fibHelper(x - 1, next, next + prev)
    }
    fibHelper(x)
  }

}
