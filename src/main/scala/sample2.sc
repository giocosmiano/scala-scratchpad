import scalaz._
import Scalaz._
import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import scala.util.{Failure, Success, Try}

val DATE_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
val DATE_TIME_PATTERN2 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSZ")
val DATE_TIME_PATTERN3 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SZ")
val LIST_OF_DATE_PATTERNS = List(DATE_TIME_PATTERN, DATE_TIME_PATTERN2, DATE_TIME_PATTERN3)

def parseDateOnListOfValidFormats(date: String
                                  , validDateFormats: List[DateTimeFormatter]
                                 ): Option[DateTime] = {
  var dateTime: Option[DateTime] = None
  Try(DateTime.parse(date, validDateFormats.head).withZone(DateTimeZone.UTC)) match {
    case Success(parsedDateTime) => dateTime = Some(parsedDateTime)
    case Failure(_) => validDateFormats match {
      case _ :: tail => dateTime = parseDateOnListOfValidFormats(date, tail)
      case _ => None
    }
  }
  dateTime
}

def parseLastModifiedDate(value: String): Option[DateTime] = {
  var lastModifiedDate: Option[DateTime] = None
  if (Option(value).nonEmpty) {
    lastModifiedDate = parseDateOnListOfValidFormats(value, LIST_OF_DATE_PATTERNS)
  }
  lastModifiedDate
}

val s1 = DateTime.now(DateTimeZone.UTC)
val d1 = parseLastModifiedDate("2018-08-10T14:36:02.331Z")
val d2 = parseLastModifiedDate("2018-08-10T14:36:02.33Z")
val d3 = parseLastModifiedDate("2018-08-10T14:36:02.3Z")

val z1 = d1.nonEmpty && d1.get.withTimeAtStartOfDay.isEqual(s1.withTimeAtStartOfDay)
val z2 = d2.nonEmpty && d1.get.withTimeAtStartOfDay.isEqual(s1.withTimeAtStartOfDay)
val z3 = d3.nonEmpty && d1.get.withTimeAtStartOfDay.isEqual(s1.withTimeAtStartOfDay)

println(s"z1=$z1, z1=$z2, z1=$z3")

val x1 = parseLastModifiedDate("2018-04-13T14:44:09.502Z")
val m1 = if (x1.nonEmpty) x1.get.getMillis else 0
println(s"x1=$x1, m1=$m1")
