// ###################################
// #  Object Orientated Programming  #
// ###################################

/** Classes, Objects, Inheritance, Traits */

// Method 1 - unimplemented members
class ThemeParkEmptyParams (val hasRollerCoasters: Boolean, val diameterOfLargestLoop: Double)


// Method 2 - implemented members
// Must use curly braces {}
class ThemeParkDefaultParams {
  val hasRollerCoasters: Boolean = true
  val diameterOfLargestLoop: Double = 35.68
}
// Everything inside the curly braces belongs to the class - known as members of that class


// This example - diameter always changes
class ThemeParkBoth(val diameterOfLargestLoop: Double){
  val hasRollerCoasters: Boolean = true
}


/** Objects */

// Objects made of: State & Behaviour & Identity
// State: Attributes
// Behaviour: Methods
// Identity: Name

  // Empty Params
val myPark: ThemeParkEmptyParams = new ThemeParkEmptyParams(hasRollerCoasters = true, diameterOfLargestLoop = 8.7)
myPark.getClass
myPark.diameterOfLargestLoop

  // Default Params
val newPark: ThemeParkDefaultParams = new ThemeParkDefaultParams
newPark.getClass
newPark.diameterOfLargestLoop

  // Default and Empty
val newPark2: ThemeParkBoth = new ThemeParkBoth(diameterOfLargestLoop = 20)
newPark2.getClass
newPark2.diameterOfLargestLoop


// ###################################

class Hamster (age: Int, colour: String)
val orange: String = "Orange"
val myHamster:Hamster = new Hamster(age=1, colour=orange)

/** Objects Without Classes - Singleton Objects*/
// Can only be used once
// have no params & available globally

object TomsDogs{
  val Varni: String = "Varni"
  val Meg: String = "Meg"
  val Buster: String = "Buster"
}

TomsDogs.Varni

/** Inheritance */
// use the extends keyword
// child extends parent/superclass
// can only extend one parent class

class AltonTowers extends ThemeParkDefaultParams{
  override val diameterOfLargestLoop: Double = 20
  val openingHour: Int = 9
  val closingHour: Int = 20

  def isOpen(time:Int): Boolean = {
    time > openingHour && time < closingHour
  }

}

class ThorpePark extends ThemeParkEmptyParams(hasRollerCoasters = true, diameterOfLargestLoop = 30){
  val maxCapacity: Int = 20000
}

val altonTowers: AltonTowers = new AltonTowers
altonTowers.isOpen(10)


/** Traits */
// keyword - with
// can extend multiple traits
// traits can have unimplemented members
// we cannot create an instance of a trait

trait Mascot{
  val nameOfCharacter: String
}
trait GiantFood{
  val hugeTurkeyLeg: Boolean
}

class DisneyWorld extends ThemeParkDefaultParams with Mascot with GiantFood {
  override val nameOfCharacter: String = "Mickey Mouse"
  override val hugeTurkeyLeg: Boolean = true
}