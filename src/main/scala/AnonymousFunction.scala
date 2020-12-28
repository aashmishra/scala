object AnonymousFunction  extends App{

  val doubler = new Function1[Int,Int] {
    override def apply(v1: Int): Int = v1+v1
  }

  // Anonymous Function (LAMBDA)
  val doubler1 = (x: Int) => x*2
  val doubler2: Int => Int = (x: Int) => x*2
  val doubler3: Int => Int = x => x*2
//  doubler and doubler1 are same

  val adder: (Int,Int) => Int = (a:Int,b:Int) => a+b

//  No Parameter
  val justDoSomething:()=>Int = () => 3

  println(justDoSomething)  //Function itself
  println(justDoSomething()) // call

  // curly braces with Lambda
  val stringToInt ={ (str:String)=>
    str.toInt
  }

  //MoRe syntactic sugar

  val niceIncrementer: Int => Int = _+1  // equivalent to x=>x+1
  val niceAdder: (Int,Int)=> Int = _ + _  // equivalent to (a,b)=> a+b


  val specialFunction :Function1[Int,Function1[Int,Int]] = new Function1[Int,Function1[Int,Int]] {
    override def apply(x: Int): Function1[Int,Int] = new Function1[Int,Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val specialFunction1 :Int=>(Int=>Int)= (x:Int)=> (y:Int) => x+y

  val adder3 = specialFunction1(10)
  println(adder3(20))
  println(specialFunction1(10)(14))  //curried function

}

