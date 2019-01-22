import scala.annotation.tailrec

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

val x = BigInt(50000)
println(s"factorial of $x")
println(s"factorial == ${factorial(x)}")
println(s"factorial2 == ${factorial2(x)}")
