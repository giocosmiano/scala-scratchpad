package tailRec

import com.typesafe.scalalogging.StrictLogging

import scala.annotation.tailrec

// https://alvinalexander.com/scala/scala-recursion-examples-recursive-programming
//
// Martin Odersky's "Programming in Scala 2nd edition",
// https://stackoverflow.com/questions/12496959/summing-values-in-a-list
object Factorial extends App with StrictLogging {

  val x = BigInt(50000)
  println(s"factorial of $x")
  println(s"factorial == ${factorial(x)}")
  println(s"factorial2 == ${factorial2(x)}")

  // 1 - tail-recursive factorial method
  def factorial(n: BigInt): BigInt = {
    @tailrec
    def factorialAccumulator(acc: BigInt, n: BigInt): BigInt = {
      if (n == 0) acc
      else factorialAccumulator(n*acc, n-1)
    }
    factorialAccumulator(1, n)
  }

  // 2 - basic recursive factorial method
  def factorial2(n: BigInt): BigInt = {
    if (n <= 1) 1
    else n * factorial(n-1)
  }
}
