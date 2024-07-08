/**  ---- Code Evaluation ---- */
/*
Make abstract classes without () and use {} instead
this will prevent you from having to list all of the parameters over and over
-- CASE CLASSES -- "pimped up classes"
 */



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


abstract class Animal {
  val name: String
  val age: Int
  val diet: DietType
}


abstract class Mammal extends Animal {
  val furColour:String
  val hasTail:Boolean}


abstract class Bird extends Animal {
  val beakLength:Double
  val featherColour:String}


abstract class Insect extends Animal{
  val numOfLimbs:Int
}


class Warthog(
               val name: String,
               val speciesName: String,
               val age: Int,
               val furColour: String,
               val hasTail: Boolean,
               val hasTusks: Boolean = true
             ) extends Mammal with Species {
  val conservationStatus: String = "Least Concern"
  val avgLifespanRange: (Int, Int) = (7, 11)
  val numOfYoungRange: (Int, Int) = (3, 4)
  override val diet: DietType = Herbivore
}


class Owl(val speciesName:String,
          val conservationStatus: String, 
          val avgLifespanRange:(Int,Int),
          val numOfYoungRange: (Int, Int), 
          val canFly: Boolean, 
          val wingspan:Double) 
  extends Bird with Species with Flyable {
  override val beakLength: Double = 5.2
  override val featherColour: String = "white"
  override val name: String = "Barny"
  override val age: Int = 4
  override val diet: DietType = Carnivore
}


class Dragonfly(val wingPairs: Int,
                val speciesName:String, 
                val conservationStatus: String, 
                val avgLifespanRange:(Int,Int),
                val numOfYoungRange: (Int, Int), 
                val canFly: Boolean, 
                val wingspan:Double) 
  extends Insect with Species with Flyable {
  override val numOfLimbs: Int = 6
  override val name: String = "Drago"
  override val age: Int = 1
  override val diet: DietType = Herbivore
}


class Domesticated(val breed:String,
                  ) extends Animal {
  override val name: String = "Dom"
  override val diet: DietType = Carnivore
  override val age: Int = 3
}


class Dog(
         name:String,
         age:Int,
         diet:DietType,
         breed:String,
         val isHouseTrained:Boolean
         ) extends Domesticated(breed)


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
  furColour = "Brown",
  hasTail = true,
  hasTusks = true,
  speciesName = "Phacochoerus africanus")

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

