object WhatsAFunction extends App {
  val doubler =  new MyFunction[Int, Int] {
    override def apply(element : Int):Int = element*2
  }
  trait MyFunction[A,B] {
    def apply(element: A):B
  }
  println(doubler(2))

  //function types  = Function[A,B]
  val stringToIntConverter = new Function1[String,Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3")+4)

//  val adder :Function2[Int,Int,Int] =  new Function2[Int,Int,Int] {
//    override def apply(v1: Int, v2: Int): Int = v1+v2
//  }
//Function types Function2[A,B,R] === (A,B) => R

  val adder :((Int, Int)=>Int) =  new Function2[Int,Int,Int] {
    override def apply(v1: Int, v2: Int): Int = v1+v2
  }
  println(adder(4,5))

  val concat :((String, String)=>String) =  new Function2[String,String,String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concat("Jami:Hi, I am here " , "\nWrick:And I am also here"))

  val specialFunction :Function1[Int,Function1[Int,Int]] = new Function1[Int,Function1[Int,Int]] {
    override def apply(x: Int): Function1[Int,Int] = new Function1[Int,Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = specialFunction(3)
  println(adder3(4))
  println(specialFunction(4)(5))  //curried function


  val superAdder: Int=>(Int=>Int) = (x:Int)=>(y:Int) => x+y
//  val add3 = superAdder(3)  // y =>3+y
//  println(add3(10))
//  println(superAdder(3)(4))

  def toCurry(f:(Int,Int)=>Int):(Int=>Int=>Int) =
   x=>y=>f(x,y)
  def fromCurry(f:(Int=>Int=>Int)):(Int,Int)=>Int =
    (x,y)=>f(x)(y)

  def compose[A,B,T](f:A=>B,g:T=>A):T=>B=
    x=>f(g(x))
  def andThen[A,B,C](f:A=>B,g:B=>C):A=>C=
    x=>g(f(x))

 def superAdder2:(Int=>Int=>Int) = toCurry(_+_)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,17))
//  println(superAdder(4,17))


  val add2 =(x:Int)=>x+2
  val times3 = (x:Int)=>x*3

  val composed = compose(add2,times3)
  val ordered = andThen(add2,times3)

  println(composed(4))
  println(ordered(4))



}
