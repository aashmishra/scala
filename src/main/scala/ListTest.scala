abstract class MyList[+A] {

  /**
    *
    * head = first element of the List
    * tail= remainder of the List
    * isEmpty = is this List Empty
    * add(int) => new List with this element added
    * toString => a String representation of List
    *
    */
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B>:A](element:B):MyList[B]
   def printElement :String
  //polymorphic call
  override def toString: String = "[" + printElement + "]"
  def map[B](transform: MyTransform[A, B]): MyList[B]
  def flatMap[B](transform: MyTransform[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]):MyList[A]

  def ++[B>:A] (list:MyList[B]):MyList[B]

}

case object Empty extends MyList[Nothing]{
  def head = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B>:Nothing](element:B):MyList[B] = new Cons(element, Empty)
  def printElement: String =""
  def map[B](transform: MyTransform[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transform: MyTransform[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]):MyList[Nothing] = Empty

  def ++[B>:Nothing] (list:MyList[B]):MyList[B]=list

}

trait MyPredicate[-T]{
  def test(element :T):Boolean
}

trait MyTransform[-A, B] {
  def transform(element: A):B
}

case class Cons[+A](h: A, t: MyList[A] ) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B>:A](element:B):MyList[B] = new Cons(element, this)
  def printElement: String = if(t.isEmpty) "" + h else h + " " + t.printElement
  def map[B](transform: MyTransform[A, B]): MyList[B] = new Cons(transform.transform(h), t.map(transform))

    /**
      * [1,2] ++ [3,4,5]
      * new Cons(1, [2] ++ [3,4,5])
      * new Cons(1, new Cons(2, Empty ++ [3,4,5]))
      * new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))
      * @param list
      * @tparam B
      * @return
      */
  def ++[B>:A] (list:MyList[B]):MyList[B] = new Cons(h, t ++ list)

  /**
    * [1,2].flatMap(n=>[n, n+1])
    * [1,2] ++ [2].flatMap(n=>[n, n+1])
    * [1,2] ++ [2,3] ++ Empty.flatMap(n=>[n, n+1])
    * [1,2] ++ [2,3] ++ [Empty])
    * [1,2,2,3,Empty]
    * @param transformer
    * @tparam B
    * @return
    */
  def flatMap[B](transformer: MyTransform[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def filter(predicate: MyPredicate[A]):MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)





}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listClone = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherList = new Cons(6, new Cons(7, new Cons(8, Empty)))
  println(list.tail.head)
  println(list.toString)

  println(list.map(new MyTransform[Int, Int] {
    override def transform(element: Int): Int = element*2
  }))

  println(list.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element==2
  }))

  println((list ++ anotherList).toString)
  println(list.flatMap(new MyTransform[Int,MyList[Int]] {
    override def transform(element: Int): MyList[Int] = new Cons(element, new Cons(element+1, Empty))
  }).toString)

  println(list==listClone)
}