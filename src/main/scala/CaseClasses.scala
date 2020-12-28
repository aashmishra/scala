object CaseClasses {

  // Quick light weight data Structure
  // case classes are an exceptionally useful shorthand for defining a class and the companion object
  // and a lot of sensible defaults in one go
  // This is really perfect for creating this kind of lightweight data holding classes with minimal hassle
  // equals, hashcode, toString
case class Snake(name: String, length:Float)

  // 1.class parameters are fields
  val cobra = new Snake("cobra", 15.8F)

  // 2. sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(cobra)

  // 3. equals and hashcode implemented
  val blackCobra = new Snake("cobra",15.8F)
  println(cobra== blackCobra)

  // 4. CCs have already handy method
  val whitecobra = cobra.copy(name="whiteCobra")
  println(whitecobra)

  // 5. CCs have companion Objects
  val cobranew = Snake
  val shortCobra = Snake("shortCobra", 2.3F)

  // 6. CCs are serializable
  //AKKA


  // 7. CCs have extractor patterns = CCs can be used in pattern matching


  case object hello {
    val message ="Hello World!"
  }
}
