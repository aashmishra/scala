object MapFlatMapFilterFor extends App {

  val list = List (1,2,3)
  println(list.head)
  println(list.tail)

    //map
  println(list.map(_ + 1))
  println(list.map(_ + "is a number"))

  //filter
  println(list.filter(_ %2 ==0))

  //flatmap
  val toPair = (x:Int)=>List(x,x+1)
  println(list.flatMap(toPair))

  //print all combination between lists
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black","white")

  val combination1 = numbers.flatMap(n=> chars.map(c=>" "+ c + n))
  println(combination1)

  val combination2 = numbers.flatMap(n=> chars.flatMap(c=>colors.map(col=>col+" "+ c + n)))
  println(combination2)

 //foreach
  list.foreach(println)

  //for-comprehension
  val forCombinations = for {
    n <-numbers if n%2 ==0
    c <-chars
    color<-colors
  } yield " " +c + n + "-" + color

  println(forCombinations)
}
