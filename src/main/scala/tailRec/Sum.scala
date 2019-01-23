package tailRec

import com.typesafe.scalalogging.StrictLogging

import scala.annotation.tailrec

// https://alvinalexander.com/scala/scala-recursion-examples-recursive-programming
//
// Martin Odersky's "Programming in Scala 2nd edition",
// https://stackoverflow.com/questions/12496959/summing-values-in-a-list
object Sum extends App with StrictLogging {

  val list = List.range(1, 1000)
  println(s"list 1..${list.last}")
  println(s"sum == ${sum(list)}")
  println(s"sum2 == ${sum2(list)}")
  println(s"sum3 == ${sum3(list)}")
  println(s"sumWithReduce == ${sumWithReduce(list)}")
  println(s"sumSum == ${sumSum(list)}")

  // (1) yields a "java.lang.StackOverflowError" with large lists
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case x :: tail => x + sum(tail)
  }

  // (2) tail-recursive solution
  def sum2(ints: List[Int]): Int = {
    @tailrec
    def sumAccumulator(ints: List[Int], accum: Int): Int = {
      ints match {
        case Nil => accum
        case x :: tail => sumAccumulator(tail, accum + x)
      }
    }
    sumAccumulator(ints, 0)
  }

  // (3) good descriptions of recursion here:
  // stackoverflow.com/questions/12496959/summing-values-in-a-list
  // this example is from that page:
  def sum3(xs: List[Int]): Int = {
    if (xs.isEmpty) 0
    else xs.head + sum3(xs.tail)
  }

  def sumWithReduce(ints: List[Int]): Int = {
    ints.reduceLeft(_ + _)
  }

  def sumSum(ints: List[Int]): Int = {
    ints.sum
  }
}
