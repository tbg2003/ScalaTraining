// ###################################
// #              MVP                #
// ###################################

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