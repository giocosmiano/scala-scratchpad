import scala.annotation.tailrec

// 1 - using `match`
def max(ints: List[Int]): Int = {
  @tailrec
  def maxAccum(ints: List[Int], theMax: Int): Int = {
    ints match {
      case Nil => theMax
      case x :: tail =>
        val newMax = if (x > theMax) x else theMax
        maxAccum(tail, newMax)
    }
  }
  maxAccum(ints, 0)
}

// 2 - using if/else
def max2(ints: List[Int]): Int = {
  @tailrec
  def maxAccum2(ints: List[Int], theMax: Int): Int = {
    if (ints.isEmpty) {
      theMax
    } else {
      val newMax = if (ints.head > theMax) ints.head else theMax
      maxAccum2(ints.tail, newMax)
    }
  }
  maxAccum2(ints, 0)
}

def maxWithReduce(ints: List[Int]): Int = {
  ints.reduceLeft(_ max _)
}

def maxMax(ints: List[Int]): Int = {
  ints.max
}

val list = List.range(1, 5000000)
println(s"list 1..${list.last}")
println(s"max           == ${max(list)}")
println(s"max2          == ${max2(list)}")
println(s"maxWithReduce == ${maxWithReduce(list)}")
println(s"maxMax        == ${maxMax(list)}")
