object MyListExample {

//  abstract  class MyList{
//    def head :Int
//    def tail:MyList
//    def isEmpty:Boolean
//    def add(element :Int):MyList
//    def printElements:String
//    //polymorphic call
//    override def toString:String = "[" + printElements + "]"
//
//  }
//
//  object Empty extends MyList{
//    def head:Int = throw new NoSuchElementException
//    override def tail: MyList = throw new NoSuchElementException
//    override def isEmpty: Boolean = true
//    override def add(element: Int): MyList = new Cons(element,Empty)
//    override def printElements: String = ""
//  }
//  class Cons(h: Int, t: MyList) extends  MyList {
//    override def head: Int = h
//    override def tail: MyList = t
//    override def isEmpty: Boolean = false
//    override def add(element: Int): MyList = new Cons(element,this)
//    override def printElements: String = {
//      if (t.isEmpty) ""+h
//      else h +  " " + t.printElements  }
//  }
//
//  def main(args: Array[String]): Unit = {
//    val list  = new Cons(1,new Cons(2,new Cons(3, Empty)))
//    println(list.tail.head)
//    println(list.add(4).head)
//    println(list.isEmpty)
//    println(list.toString)
//  }

}
