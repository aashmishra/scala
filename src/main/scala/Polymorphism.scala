object Polymorphism extends App{

   class Student(val currentBook: String) {
     def hasBook(book : String): Boolean = book == currentBook

  }
  class Medical extends Student("Biology"){
    override def hasBook(book : String): Boolean = {
        val m = super.hasBook(book)
        !m
    }
  }
  class NonMedical extends  Student("Chemistry"){
  }
  // This is called Polymorphism
  val m:Student = new Medical
  val s:Student = new Student("math")
  val p:Student = new NonMedical
  println(m.hasBook(p.currentBook))

// final vs sealed vs private vs protected

  abstract class  Maruti(name:String){
    val wheel: String
    def run(noOfWheels: Int):Int
    final def noOtherContractor:Boolean=false
  }
  trait Scoda{
    val wheel: String
    def run(noOfWheels: Int):Int
  }

  trait Scoda1{
    val wheel: String
    def run(noOfWheels: Int):Int
  }

  trait Scoda2{
    val m = "hello"
  }

  class honda extends Maruti("hello") with Scoda with Scoda1 with Scoda2 {
    val wheel: String = "Mahindra"

    def run(noOfWheels: Int): Int = {
      noOfWheels*20000
    }
  }

  val h = new honda
  h.noOtherContractor
}
