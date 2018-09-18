package scalaz.byAlexanderWorton.sampleKleisli

import com.typesafe.scalalogging.StrictLogging
import scalaz.Kleisli
import scalaz.concurrent.Task

// https://www.youtube.com/watch?v=qL6Viix3npA
object Main2 extends App with StrictLogging {
  val getNumberFromDB: Unit => Task[Int] = _ => {
    logger.info(s"Retrieving number from DB")
    Task.now(2)
  }
  val processNumber: Int => Task[Int] = nbr => {
    logger.info(s"Processing number $nbr")
    Task.now(nbr * 2)
  }
  val writeNumberToDB: Int => Task[Boolean] = nbr => {
    logger.info(s"Writing $nbr to DB")
    Task.now(true)
  }

  def flatMap: Unit = {
    val comboFlatMap: Unit => Task[Boolean] = Unit => {
      getNumberFromDB() flatMap { number =>
        processNumber(number) flatMap { processed =>
          writeNumberToDB(processed)
        }
      }
    }

    logger.info("")
    logger.info(s"flatMap() Result: ${comboFlatMap().unsafePerformSync}")
  }

  def forComp: Unit = {
    val comboForComp: Unit => Task[Boolean] = Unit => {
      for {
        number <- getNumberFromDB()
        processed <- processNumber(number)
        result <- writeNumberToDB(processed)
      } yield result
    }

    logger.info("")
    logger.info(s"forComp() Result: ${comboForComp().unsafePerformSync}")
  }

  def comboKleisli1: Unit = {
    val getNumberFromDBKleisli: Kleisli[Task, Unit, Int] = Kleisli(getNumberFromDB)
    val processNumberKleisli: Kleisli[Task, Int, Int] = Kleisli(processNumber)
    val writeNumberToDBKleisli: Kleisli[Task, Int, Boolean] = Kleisli(writeNumberToDB)
//    val comboKleisli: Kleisli[Task, Unit, Boolean] = getNumberFromDBKleisli andThen processNumberKleisli andThen writeNumberToDBKleisli
    val comboKleisli: Kleisli[Task, Unit, Boolean] = getNumberFromDBKleisli >==> processNumberKleisli >==> writeNumberToDBKleisli

    logger.info("")
    logger.info(s"comboKleisli1() Result: ${comboKleisli().unsafePerformSync}")
  }

  def comboKleisli2: Unit = {
//    val comboKleisli: Unit => Task[Boolean] = (Kleisli(getNumberFromDB) andThenK processNumber andThenK writeNumberToDB).run
    val comboKleisli: Unit => Task[Boolean] = (Kleisli(getNumberFromDB) >==> processNumber >==> writeNumberToDB).run
    logger.info("")
    logger.info(s"comboKleisli2() Result: ${comboKleisli().unsafePerformSync}")
  }

  flatMap
  forComp
  comboKleisli1
  comboKleisli2
}
