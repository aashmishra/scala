import scala.annotation.tailrec

object Recursion extends App {


  class DataFrame (val name:String,val age: Int,val currentColValuePair:Map[String,String]=Map.empty){
    val address = "delhi"
    def inc(x:Int):DataFrame = new DataFrame(name, age+x)
    def dec(x:Int):DataFrame = new DataFrame(name, age-x)
    def addLastName(lastName:String):DataFrame = new DataFrame(name + " " + lastName, age)
    def withColumn(colName:String, columnValue:String):DataFrame = new DataFrame(name , age, currentColValuePair ++ Map(colName->columnValue))
  }
  val DF = new DataFrame("Ashish",29)

  println(DF.age)

  println(DF.inc(10).dec(10).inc(10).dec(10).age)
  println(DF.withColumn("name","kishna").withColumn("address","barmer").currentColValuePair)



  var z = System.nanoTime()
  def showByValue(x:Long):Unit=
  { println(x)
    Thread.sleep(10)
    println(x) }

  def showByRef(x: => Long):Unit=
  { println(x)
    Thread.sleep(10)
    println(x) }

  showByValue(System.nanoTime())
  showByRef(System.nanoTime())


  /**
    * fibonacci
    * factorial
    * isPrime
    * Counter
    */


  // 0 1 1 2 3 5 8
  // 0 1 2 3 4 5 6

  def fibonacci(x:BigInt):BigInt ={
    @tailrec
    def fibonacciHelper(x:BigInt,till:BigInt, accumulatorOne: BigInt,  accumulatorTwo: BigInt):BigInt={
      if(x==till) accumulatorOne+accumulatorTwo else fibonacciHelper(x+1,till, accumulatorTwo, accumulatorOne+accumulatorTwo)
    }
    if (x<1) 0 else if (x==1) 1 else fibonacciHelper(2,x,0,1)
  }
//
//
//    println(fibonacci(0))
//    println(fibonacci(1))
//    println(fibonacci(2))
//    println(fibonacci(3))
//    println(fibonacci(4))
//    println(fibonacci(5))
//    println(fibonacci(6))
//    println(fibonacci(100000))
//
//
  def factorial(x:BigInt):BigInt={
    if (x<=1) 1 else x*factorial(x-1)
  }

  def factorialTailRec(x:BigInt):BigInt={
    @tailrec
    def helper(x:BigInt, accu:BigInt=1):BigInt={
      if (x<=1) x*accu  else helper(x-1,x*accu)
    }
    helper(x)
  }
  println(factorial(5000))
  println(factorialTailRec(5000))
//  5/4
//  5/3
//  5/2
//  5/1
//  5/0

  def isPrime(x:BigInt):Boolean={
    @tailrec
    def helper(x:BigInt, divider:BigInt):Boolean  = {
      if (divider<=1) true else if (x%divider == 0 && divider>1) {
        println(x +" is divisible by " + divider )
        false } else helper(x, divider-1)
    }
    if (x<=3) false else helper(x,x/2)
  }
  println(isPrime(17))


class Counter(value: Int){
    def inc:Counter = new Counter(value+1)
    def inc(n:Int):Counter = if (n<=0) this else inc.inc(n-1)
  }




}
