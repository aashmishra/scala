object  patternMatch extends App{
  //val x: String = (1 to 65000000).toList.
  //  map(x=>x.toString()).
  //  mkString(""""""","""","""",""""""")
  //
  //print(x.length)
  //print(x)
  import java.time.{LocalDate, LocalDateTime, LocalTime}

  object DateTime {
    def unapply(dt: LocalDateTime): Some[(LocalDate, LocalTime)] =
      Some((dt.toLocalDate, dt.toLocalTime))
  }

  object Date {
    def unapply(d: LocalDate): Some[(Int, Int, Int)] =
      Some((d.getYear, d.getMonthValue, d.getDayOfMonth))
  }

  object Time {
    def unapply(t: LocalTime): Some[(Int, Int, Int)] =
      Some((t.getHour, t.getMinute, t.getSecond))
  }



  object AM {
    def unapply(t: LocalTime): Option[(Int, Int, Int)] =
      t match {
        case Time(h, m, s) if h < 12 => Some((h, m, s))
        case _ => None
      }
  }

  object PM {
    def unapply(t: LocalTime): Option[(Int, Int, Int)] =
      t match {
        case Time(12, m, s) => Some(12, m, s)
        case Time(h, m, s) if h > 12 => Some(h - 12, m, s)
        case _ => None
      }
  }

  val data = LocalTime.now match {
    case AM(h, m, _) =>
      f"$h%2d:$m%02d AM (precisely)"
    case PM(h, m, _) =>
      f"$h%2d:$m%02d PM ( precisely)"
  }
 print(data)
  print(LocalTime.now)

  case class Student(name: String, address: Seq[Address])
  case class Address(city: String, state: String)

  object City {
    def unapply(s: Student): Option[Seq[String]] =
      Some(
        for (c <- s.address)
          yield c.state)
  }

  class StringSeqContains(value: String) {
    def unapply(in: Seq[String]): Boolean =
      in contains value
  }

  object PatternMatch {
    def main(args: Array[String]) {

      val stud = List(Student("Harris", List(Address("LosAngeles", "California"))),
        Student("Reena", List(Address("Houston", "Texas"))),
        Student("Rob", List(Address("Dallas", "Texas"))),
        Student("Chris", List(Address("Jacksonville", "Florida"))))

      val Texas = new StringSeqContains("Texas")
      val students = stud collect {
        case student @ City(Texas()) => student.name
      }
      println(students)
    }
  }


}
