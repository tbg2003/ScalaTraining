// ###################################
// #       MVP & Extension           #
// ###################################

// sealed trait
// compiler knows every possible subtypes and can reason about it
sealed trait DietType
case object Herbivore extends DietType
case object Carnivore extends DietType
case object Omnivore extends DietType

trait Species{
  val speciesName:String
  val conservationStatus:String
  val avgLifespanRange: (Int, Int)
  val numOfYoungRange: (Int, Int)
}


trait Flyable{
  val canFly:Boolean
  val wingspan:Double
}


class Animal(val name:String, val age:Int, val diet:DietType)


class Mammal(name:String, age:Int, diet:DietType, val furColour:String, val hasTail:Boolean) extends Animal(name, age, diet)


class Bird(name:String, age:Int, diet:DietType, val beakLength:Double, val featherColour:String) extends Animal(name, age, diet)


class Insect(name:String, age:Int, diet:DietType, val numOfLimbs:Int) extends Animal(name, age, diet)


class Warthog(name:String, 
              age:Int,
              diet:DietType,
              furColour:String, 
              hasTail:Boolean, 
              val hasTusks:Boolean, 
              val speciesName:String, 
              val conservationStatus: String, 
              val avgLifespanRange:(Int,Int),
              val numOfYoungRange: (Int, Int)) 
  extends Mammal(name, age, diet, furColour, hasTail) with Species


class Owl(name:String, 
          age:Int, beakLength:Double,
          diet:DietType,
          featherColour:String, 
          val hootsPerNight:Int, 
          val speciesName:String, 
          val conservationStatus: String, 
          val avgLifespanRange:(Int,Int),
          val numOfYoungRange: (Int, Int), 
          val canFly: Boolean, 
          val wingspan:Double) 
  extends Bird(name, age, diet, beakLength, featherColour) with Species with Flyable


class Dragonfly(name:String, 
                age:Int, numOfLimbs:Int,
                diet:DietType,
                val wingPairs: Int, 
                val speciesName:String, 
                val conservationStatus: String, 
                val avgLifespanRange:(Int,Int),
                val numOfYoungRange: (Int, Int), 
                val canFly: Boolean, 
                val wingspan:Double) 
  extends Insect(name, age, diet, numOfLimbs) with Species with Flyable


class Domesticated(name:String,
                   age:Int,
                   diet:DietType,
                   val breed:String,
                  ) extends Animal(name, age, diet)


class Dog(
         name:String,
         age:Int,
         diet:DietType,
         breed:String,
         val isHouseTrained:Boolean
         ) extends Domesticated(name, age, diet, breed)


object Sanctuary{
// Thinking of how to implement a val?
  var animals:Map[String, Animal] = Map()

  def add_animal(animal: Animal): Unit = {
    animals += (animal.name -> animal)
  }
}
val warthog1 = new Warthog(
  name = "Pumbaa",
  age = 6,
  diet = Herbivore,
  furColour = "Brown",
  hasTail = true,
  hasTusks = true,
  speciesName = "Phacochoerus africanus",
  conservationStatus = "Least Concern",
  avgLifespanRange = (7, 11),
  numOfYoungRange = (1, 7))

val myDog = new Dog(
  name = "Varni",
  age = 7,
  diet = Carnivore,
  breed = "Cavalier Kings Charles Spaniel",
  isHouseTrained = true
)

Sanctuary.add_animal(myDog)
Sanctuary.add_animal(warthog1)
Sanctuary.animals

// ###################################
// #            Research             #
// ###################################

/** Difference Between abstract class and a trait? */
/*
* Abstract classes cannot be instantiated directly
* They can have members with and without implementation
* Used to define a common base type with some shared implementation and some
* methods that must be defined by subclasses
* Similar to traits, but traits are more flexible and DO NOT take constructor parameters
*
* USE CASES:
*
*  - Normal Class -
* When you have a concrete implementation you want to instantiate directly & use
*
*  - Abstract Class -
* When you need to define a common base type with some shared implementation and some
* abstract methods that must be implemented by subclasses
* Good for when you have a hierarchy
*
*  - Traits -
* When you need to define reusable behaviour that can be mixed into different classes
* Great for defining behaviours that can be shared across unrelated domains
*
* */

/** What are companion classes and objects? Why might you use them?*/
/*
* A companion object is an object which shares the same name with a class
* within the same file
*
* It can:
*  - Hold specific static-like members that are shared across all instances of the class
*  - Access both private methods and private fields of the class.
*
* A companion class is a normal class however:
*     It can hold instance-specific data and behaviour
*
* USE CASES:
*
*  - Companion Objects -
*
* Use to define FACTORY METHODS that create instances if the companion class
* Makes the instantiation process more readable and provide
* additional logic during object creation
*
* Use to hold STATIC-LIKE MEMBERS that do not belong to a specific
* instance but are related to the class as a whole
* Allows you to group together related functionality and access it without needing
* and instance of the class - similar to static methods
*
*
* ENCAPSULATION and ACCESS CONTROL
* Use companion Objects when you need to access private members of the companion class
* As they can access private members, useful to manipulate internal state of class
* Maintains encapsulation while allowing complex interactions within class-object pair
*
* */

