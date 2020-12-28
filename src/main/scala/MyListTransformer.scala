object MyListTransformer {
    abstract  class MyList[+A]{
      def head :A
      def tail:MyList[A]
      def isEmpty:Boolean
      def add[B >:A](element:B):MyList[B]
      def printElements:String
      //polymorphic call
      override def toString:String = "[" + printElements + "]"
//      def map[B](transformer:MyTransformer[A,B]):MyList[B]
      def map[B](transformer:A=> B):MyList[B]

//      def flatMap[B](transformer : MyTransformer[A, MyList[B]]):MyList[B]
def flatMap[B](transformer : A=> MyList[B]):MyList[B]
      def filter(predicate: MyPredicate[A]):MyList[A]
      def ++[B>:A](list:MyList[B]):MyList[B]

      def foreach(f:A=>Unit):Unit
      def sort(compare:(A,A)=>Int):MyList[A]
      def zipWith[B, C](list:MyList[B],zip:(A,B)=>C): MyList[C]
      def fold[B](start:B)(operator:(B,A)=>B):B

    }

    object Empty extends MyList[Nothing]{
      def head:Nothing = throw new NoSuchElementException
      override def tail: MyList[Nothing] = throw new NoSuchElementException
      override def isEmpty: Boolean = true
      override def add[B>:Nothing](element: B): MyList[B] = new Cons(element,Empty)
      override def printElements: String = ""

      override def toString:String = "[" + printElements + "]"
//      def map[B](transformer:MyTransformer[Nothing,B]):MyList[B] = Empty
//      def flatMap[B](transformer : MyTransformer[Nothing, MyList[B]]):MyList[B] =Empty
def map[B](transformer:Nothing=>B):MyList[B] = Empty
      def flatMap[B](transformer : Nothing=> MyList[B]):MyList[B] =Empty
      def filter(predicate: MyPredicate[Nothing]):MyList[Nothing] = Empty

      //Empty concatenating with anything will return that list
      def ++[B>:Nothing](list:MyList[B]):MyList[B]= list
      def foreach(f:Nothing=>Unit):Unit= ()
      def sort(compare:(Nothing,Nothing)=>Int):MyList[Nothing] = Empty

      def zipWith[B,C] (list:MyList[B],zip: (Nothing, B)=>C): MyList[C] =
        if (!list.isEmpty) throw new RuntimeException("List do not have the same length")
        else Empty
      def fold[B](start:B)(operator:(B,Nothing)=>B):B= start
    }
    class Cons[+A](h: A, t: MyList[A]) extends  MyList[A] {
      override def head: A = h
      override def tail: MyList[A] = t
      override def isEmpty: Boolean = false
      override def add[B >:A](element:B): MyList[B] = new Cons(element,this)
      override def printElements: String = {
        if (t.isEmpty) ""+h
        else h +  " " + t.printElements  }

      override def filter(predicate: MyPredicate[A]): MyList[A] = {
        if (predicate.test(h)) new Cons(h, t.filter(predicate))
        else t.filter(predicate)
      }
//      override def map[B](transformer:MyTransformer[A,B]):MyList[B]= {
        override def map[B](transformer:A=>B):MyList[B]= {
//        new Cons(transformer.transform(h), t.map(transformer))
          new Cons(transformer(h), t.map(transformer))

      }
      /*
      println(listOfIntegers.sort( (x , y) => y + x ))
      println(listOfIntegers.sort( (x , y) => y - x ))
      insert(1,[2,3].sort(compare))
      insert(1,insert(2,[3].sort(compare)))
      insert(1,insert(2,new Cons(3,Empty)))
      insert(1,new Cons(3,new Cons(2,Empty)))
      new

      */

      def sort(compare:(A,A)=>Int):MyList[A] = {
          def insert(x:A,sortedList:MyList[A]):MyList[A] =
            if (sortedList.isEmpty) new Cons(x, Empty)
            else if (compare(x, sortedList.head)<=0) new Cons(x,sortedList)
            else new Cons(sortedList.head, insert(x, sortedList.tail))
          insert(h,t.sort(compare))
      }

      def zipWith[B, C] (list:MyList[B],zip:(A,B)=>C):MyList[C] =
        if (list.isEmpty) throw new RuntimeException("Lists do not have the same Length")
        else new Cons(zip(h,list.head),t.zipWith(list.tail,zip))


      override def foreach(f:A=>Unit)={
        f(h)
        t.foreach(f)
      }
      /*
      [1,2]   ++ [3,4,5]
      = new Cons(1,[2] ++ [3,4,5]
      = new Cons(1,new Cons(2,new Cons(Empty ++ [3,4,5]))
      = new Cons(1,new Cons(2,[3,4,5])
      = new Cons(1,new Cons(2,new Cons(3,new Cons(4,new Cons(5,Empty)))))
       */
      def ++[B>:A](list: MyList[B]):MyList[B] = new Cons(h,t ++ list)

      def flatMap[B](transformer: A=> MyList[B]): MyList[B] =
//        transformer.transform(h) ++ t.flatMap(transformer)
      transformer(h) ++ t.flatMap(transformer)

      def fold[B](start:B)(operator:(B,A)=>B):B =
          t.fold(operator(start,h))(operator)
    }


    trait MyPredicate[-T]
    {   // T => Boolean
      def test(elem:T):Boolean
    }
//
    trait MyTransformer[-A,B]{
      //  A=> B
      def transform(elem:A):B
    }

    def main(args: Array[String]): Unit = {
  //          val list  = new Cons(1,new Cons(2,new Cons(3, Empty)))
  //          println(list.tail.head)
  //          println(list.add(4).head)
  //          println(list.isEmpty)
  //          println(list.toString)

      val listOfIntegers : MyList[Int] = new Cons(1,new Cons(2,new Cons(3, Empty)))
      val anotherlistOfIntegers : MyList[Int] =new Cons(9,new Cons(4,new Cons(5,Empty)))
              val listOfStrings : MyList[String] = new Cons("One",new Cons("two",new Cons("three", Empty)))
      println((listOfIntegers ++ anotherlistOfIntegers).toString)
              println(listOfIntegers.toString)

              println(listOfStrings.toString)
              println(anotherlistOfIntegers.toString)


//              println(listOfIntegers.map(new MyTransformer[Int,Int] {
//      println(listOfIntegers.map(new Function1[Int,Int] {
////                  override def transform(elem: Int): Int = elem*2
//        override def apply(elem: Int): Int = elem*2
//                }))

      println(listOfIntegers.map(elem=> elem*2))


//              println(listOfIntegers.filter(new MyPredicate[Int] {
//                override def test(elem: Int): Boolean = elem % 2 == 0
//              })).toString

                    println(listOfIntegers.filter(elem=>elem%2 ==0).toString)

//      println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
//      println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
//        //        override def transform(elem: Int): MyList[Int] = new Cons(elem,new Cons(elem+1,Empty))
//        override def apply(elem: Int): MyList[Int] = new Cons(elem,new Cons(elem+1,Empty))
//      })).toString


      println(listOfIntegers.flatMap(elem=>  new Cons(elem,new Cons(elem+1,Empty))).toString)
      listOfIntegers.foreach(println)

      println(listOfIntegers.sort( (x , y) => y - x ))
      println(listOfIntegers.sort( (x , y) => x - y ))
      println(anotherlistOfIntegers.zipWith[String,String](listOfStrings, _ + "-" + _ ))
      println(anotherlistOfIntegers.fold(5)(_ + _))
    }

  //  class Cons[+A](h: A, t: MyList[A]) extends  MyList[A] {
  //  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
  //    transformer.transform(h) ++ t.flatMap(transformer)}

  //  object Empty extends MyList[Nothing]{
  //          def flatMap[B](transformer : MyTransformer[Nothing, MyList[B]]):MyList[B] =Empty}




}
