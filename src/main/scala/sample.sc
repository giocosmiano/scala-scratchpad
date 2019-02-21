import java.net.{URLDecoder, URLEncoder}

import scalaz._
import Scalaz._

/*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
*/

val DEFAULT_DELIMITER = "__&&__"

val x = 5

val error = if (x === x) false else true

val e1 = ""
val e2 = "\n\t"
val e3 = "hello"
val e4 = Option.empty[String].orNull

Option(e1).isEmpty
Option(e2).isEmpty
Option(e3).isEmpty
Option(e4).isEmpty

Option(e1).nonEmpty
Option(e2).nonEmpty
Option(e3).nonEmpty
Option(e4).nonEmpty

val e1Empty = e1 == ""
println(s"is e1 empty? $e1Empty")

val s1: Option[String] = None
val s2: Option[String] = Some("")
val s3: Option[String] = Some("\n\t")
val s4: Option[String] = Some("\n\tabc\n\t")

val x1 = s1.getOrElse("")
val x2 = s2.getOrElse("")
val x3 = s3.getOrElse("")
val x4 = s4.getOrElse("")

x1.trim.isEmpty
x2.trim.isEmpty
x3.trim.isEmpty
x4.trim.isEmpty

x1.trim.nonEmpty
x2.trim.nonEmpty
x3.trim.nonEmpty
x4.trim.nonEmpty

val h = "hello world"
val h1 = h.flatMap { "." + _ }
val h2 = h.map { "." + _ }
println(s"$h1")
println(s"$h2")

val zResult1 =
  for {
    z1 <- s1
    z2 <- s2
    z3 <- s3
    z4 <- s4
  } yield Tuple4(z1, z2, z3, z4)

val zResult2 =
  for {
    z2 <- s2
    z3 <- s3
    z4 <- s4
  } yield Tuple3(z2, z3, z4)

val zResult3 = s2 |@| s3 |@| s4
println(zResult2)
println(s"zResult2==$zResult2")
println(s"zResult3==$zResult3")


// --------------------------------------------------------------------------------------
// using some/none from scalaz
val o: Option[Int] = Some(3)

// both of these are the same, using `some/none` as oppose to `map/getOrElse`
val o1 = o map { _ + 3 } getOrElse { 42 }
val o2 = o some { _ + 4 } none { 42 }
println(s"o1==$o1")
println(s"o2==$o2")

val o3 = ~o
val o4 = ~(None: Option[Int])
println(s"o3==$o3")
println(s"o4==$o4")

// --------------------------------------------------------------------------------------
// using Validation from scalaz
def even(x: Int): Validation[NonEmptyList[String], Int] =
  if (x % 2 == 0) { x.success } else { s"$x is not an even number".wrapNel.failure }

val even4 = even(4)
val even5 = even(5)

println(s"even4==$even4")
println(s"even5==$even5")

// --------------------------------------------------------------------------------------
// using lifted Tuple creator <|*|> from scalaz

/*
val t1 = some(3) <|*|> some(5)
println(t1)
*/

// --------------------------------------------------------------------------------------
// using Monoid
val m1 = (1, "foo") |+| (2, "bar")
val m2 = mzero[(Int, String)]
println(s"m1==$m1")
println(s"m2==$m2")

val m3 = 1 + "13"
val m4 = "13" + 1
//val m5 = 1 |+| "13" // this will not compile because it's not type safe
//val m6 = "13" |+| 1 // this will not compile because it's not type safe
println(s"m3==$m3")
println(s"m4==$m4")

val im3 = identity(m3)
println(s"im3=$im3, ${identity(m3)}")

val pi = scala.math.Pi
println(f"$pi%.2f" + "%")

val pct = 15 / 90.toDouble * 100
println(f"$pct%.2f" + "%")

val qp = "belongsToChain.JudicialMatter%3E%26hasChainItem.JudicialMatter"
val url = "http://localhost:8080/ask?test=asdf%3E%26"
val urlE = URLDecoder.decode(qp, "UTF-8")
println(s"$url\n$urlE")

