object Generic {




    abstract  class MyList[+A]{
      def head :A
      def tail:MyList[A]
      def isEmpty:Boolean
      def add[B >:A](element:B):MyList[B]
      def printElements:String
      //polymorphic call
      override def toString:String = "[" + printElements + "]"

    }

    object Empty extends MyList[Nothing]{
      def head:Nothing = throw new NoSuchElementException
      override def tail: MyList[Nothing] = throw new NoSuchElementException
      override def isEmpty: Boolean = true
      override def add[B>:Nothing](element: B): MyList[B] = new Cons(element,Empty)
      override def printElements: String = ""
    }
    class Cons[+A](h: A, t: MyList[A]) extends  MyList[A] {
      override def head: A = h
      override def tail: MyList[A] = t
      override def isEmpty: Boolean = false
      override def add[B >:A](element:B): MyList[B] = new Cons(element,this)
      override def printElements: String = {
        if (t.isEmpty) ""+h
        else h +  " " + t.printElements  }
    }

    def main(args: Array[String]): Unit = {
//      val list  = new Cons(1,new Cons(2,new Cons(3, Empty)))
//      println(list.tail.head)
//      println(list.add(4).head)
//      println(list.isEmpty)
//      println(list.toString)

      val listOfIntegers : MyList[Int] = new Cons(1,new Cons(2,new Cons(3, Empty)))
      val listOfStrings : MyList[String] = new Cons("One",new Cons("two",new Cons("three", Empty)))
      println(listOfIntegers.toString)
      println(listOfStrings.toString)

    }



}
