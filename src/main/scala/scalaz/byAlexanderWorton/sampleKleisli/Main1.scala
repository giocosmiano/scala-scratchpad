package scalaz.byAlexanderWorton.sampleKleisli

import com.typesafe.scalalogging.StrictLogging

// https://www.youtube.com/watch?v=qL6Viix3npA
object Main1 extends App with StrictLogging {
  val getNumberFromDB: Unit => Int = _ => 2
  val processNumber: Int => Int = _ * 2
  val writeNumberToDB: Int => Boolean = _ => true

  val combo0: Unit => Boolean = _ => writeNumberToDB(processNumber(getNumberFromDB())) // one ugly nested function calls
  val combo1: Unit => Boolean = writeNumberToDB compose processNumber compose getNumberFromDB // function composition: right to left
  val combo2: Unit => Boolean = getNumberFromDB andThen processNumber andThen writeNumberToDB // function composition: left to right

  logger.info(s"Result: ${combo0()}")
  logger.info(s"Result: ${combo1()}")
  logger.info(s"Result: ${combo2()}")
}
