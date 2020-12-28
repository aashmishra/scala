object Security {
  // Scala program of Object Private/Protected Scope
  // package test1.test11
  class class11
  {
    private[this] var x = 1
    private var t = 2
    var z = 3
    def method11(other: class11): Unit =
    {
      println(x)
      println(t)
      println(z)

      // println(other.x)
      println(other.t)
      println(other.z)
    }
  }

  val m = new class11
  val p = new class11
  m.method11(p)

  // here on line14 x can only be
  // accessed from inside in which
  // it is defined

  // Creating object
  object GfG
  {
    // Main method
    def main(arg: Array[String])
    {
      var obj11 = new class11() //current instance created
    var y = 2
      println(obj11.method11(obj11))
      println(obj11.z)
      //println(obj11.t) //error: t cannot be accessed
      //println(obj11.x) //error: x is not a member of class11
      //according to obj11 x is not a member
    }
  }


}
