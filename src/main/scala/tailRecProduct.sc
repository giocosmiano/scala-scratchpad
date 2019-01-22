import scala.annotation.tailrec

// (1) yields a "java.lang.StackOverflowError" with large lists
def product(ints: List[BigInt]): BigInt = ints match {
  case Nil => 1
  case x :: tail => x * product(tail)
}

// (2) tail-recursive solution
def product2(ints: List[BigInt]): BigInt = {
  @tailrec
  def productAccumulator(ints: List[BigInt], accum: BigInt): BigInt = {
    ints match {
      case Nil => accum
      case x :: tail => productAccumulator(tail, accum * x)
    }
  }
  productAccumulator(ints, 1)
}

// (3) good descriptions of recursion here:
// stackoverflow.com/questions/12496959/summing-values-in-a-list
// this example is from that page:
def product3(xs: List[BigInt]): BigInt = {
  if (xs.isEmpty) 1
  else xs.head * product3(xs.tail)
}

def productWithReduce(ints: List[BigInt]): BigInt = {
  ints.reduceLeft(_ * _)
}

def productProduct(ints: List[BigInt]): BigInt = {
  ints.product
}

val list: List[BigInt] = List.range(BigInt(1),BigInt(100))
println("list 1..100")
println(s"product           == ${product(list)}")
println(s"product2          == ${product2(list)}")
println(s"product3          == ${product3(list)}")
println(s"productWithReduce == ${productWithReduce(list)}")
println(s"productProduct    == ${productProduct(list)}")
