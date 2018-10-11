val rng = (a: Int, b: Int) => (a to b).inclusive
val sum = (rng: Range) => rng.filter(_%2==0).sum
val exp2 = (rng: Range) => rng.map(scala.math.pow(_, 2)).sum
val exp3 = (rng: Range) => rng.map(scala.math.pow(_, 3)).sum

val aToB = rng(1, 5)
sum(aToB)
exp2(aToB)
exp3(aToB)

def addByCurrying (a: Int)(b: Int)(c: Int): Int = a + b + c
val addUnCurried = (a: Int, b: Int, c: Int) => a + b + c

addByCurrying(1)(2)(3)
addUnCurried(1,2,3)
