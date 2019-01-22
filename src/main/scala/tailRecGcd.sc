import scala.annotation.tailrec

def gcd(a: Int, b: Int): Int = {
  @tailrec
  def gcdHelper(a: Int, b: Int): Int = {
    if (b == 0) a else gcdHelper(b, a % b)
  }
  gcdHelper(a, b)
}

val a = 3
val b = 256

println(s"gcd $a, $b")
println(s"gcd == ${gcd(a, b)}")
