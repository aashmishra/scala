

object cocontra extends App {

  class Animal[+T](val animal:T)

  class Dog
  class Puppy extends Dog

  class AnimalCarer(val dog:Animal[Dog])

  val puppy = new Puppy
  val dog = new Dog

  val puppyAnimal:Animal[Puppy] = new Animal[Puppy](puppy)
  val dogAnimal:Animal[Dog] = new Animal[Dog](dog)

  val dogCarer = new AnimalCarer(dogAnimal)
  val puppyCarer = new AnimalCarer(puppyAnimal)

  class Food
  class Fruits extends Food
  class Sweet extends Fruits
  class Sour extends Fruits
  class Vegetable extends Food

  class Eatable[+T](val Fruits:T)

  val fruits = new Fruits
  val sweet = new Sweet
  val sour = new Sour
  val vegetable = new Vegetable
  val food = new Food

  class ChatarPattar[+T](val kucchbhi:T)
  class BholaBhala[-T](kucchbhi: Fruits)

  class Khao(val kucchbhi:ChatarPattar[Fruits])
  class Khaoji(val kucchbhi:BholaBhala[Fruits])
  val anarFruit:ChatarPattar[Fruits] = new ChatarPattar[Fruits](fruits)
  val watermelonSweet:ChatarPattar[Fruits] = new ChatarPattar[Sweet](sweet)
  val aalu:BholaBhala[Fruits] = new BholaBhala[Food](fruits)



















}
