package Week9

import scala.annotation.unused

object AfternoonTask extends App {

  abstract class Animal {
    def name: String

    def food: String
  }

  case class Dog(name: String, food: String) extends Animal

  case class Cat(name: String, food: String) extends Animal

  case class Swan(name: String, food: String) extends Animal

  val dogAnimal: Animal = Dog("buster", "dog food")
  val dog: Dog = Dog("buster", "dog food")
  val cat: Cat = Cat("tim", "cat food")

  class Solitary[T]

  //val solitaryCat:Solitary[Animal] = new Solitary[Cat]
  // doesn't compile as doesn't allow sub-type upwards

  class Pair[+T]

  //val swanPair: Pair[Swan] = new Pair[Animal]
  // doesn't compile as doesn't like super-type down

  abstract class Feeder[-T]{
    def feedAnimal(animal:T):String = println("feeding an animal").toString
  }

  case class AnimalFeeder() extends Feeder[Animal]{
    override def feedAnimal(animal: Animal): String = println(s"feeding ${animal.name} some ${animal.food}")
  }
  case class DogFeeder() extends Feeder[Dog]

  val dogFeeder: Feeder[Dog] = AnimalFeeder()
  // compiles as allows super-type down
  println(dogFeeder.feedAnimal(dog))
  //println(dogFeeder.feedAnimal(cat))
  // Doesnt compile, allows dog or sog's super not sub-type up


  abstract class Pack[+A]{
    def addToPack[B>:A](elem: B):NonEmptyPack[B] = NonEmptyPack(elem, this)
  }

  case class NonEmptyPack[+A](head:A, tail:Pack[A]) extends Pack[A]

  object EmptyPack extends Pack[Nothing]

  val dogs: NonEmptyPack[Dog] = EmptyPack.addToPack(Dog("dog", "food"))
  val cats: NonEmptyPack[Cat] = EmptyPack.addToPack(Cat("cat", "food"))
  val animals: NonEmptyPack[Animal] = dogs
  val moreAnimals: NonEmptyPack[Animal] = animals.addToPack(cats)


  abstract class Vet[-A]{
    def healAnimal[B<:A](animalToRescue:B):B
  }

}