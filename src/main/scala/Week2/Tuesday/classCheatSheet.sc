// ##############################################################
// #                   Cheat Sheet for Classes                  #
// ##############################################################


/** --- With parenthesis () --- */

// class with no defined parameters - no constructor parameters
class Park1
new Park1

// class with defined parameters - BEST PRACTISE: include val / var
// must include defined params in new instance - BEST PRACTISE: use named arguments
class Park2(val n:Int)
new Park2(n=5)

// to reference the new object must assign to val/var
val x = new Park2(n=3)
x.n

// Extending a class - must specify params when no default is set
object Park3 extends Park2(n = 5)

// ABSTRACT - Used when defined parameters have no default
abstract class Park4(val x:Int, val y:Boolean)

// cannot instantiate abstract class as object
// Abstract classes must be extended

//new Park4() -- comment out to see ERROR

// Constructing with default parameters - USE CASE: when they are unlikely to change
class ParkForKids(val maxAge:Int = 7, val noDrinksAllowed:Boolean = true)

new ParkForKids() // uses default values
new ParkForKids(maxAge=17) // Overrides the default value maxAge


/** --- With curly braces {} --- */
// class with no defined parameters
class Park5{}
new Park5

// Constructed with defined parameters
// can't use curly braces if defined have no default

//class Park6{
//  val n: Int
//  val m: Int
//} -- comment out to see ERROR

// given default values
class Park7{
  val n:Int = 1
  val m:Int = 3
}

new Park7

val y = new Park7{
  override val n: Int = 2
}
y.m
y.n

// Extending the abstract class

object AvgPark extends Park7
AvgPark.m

object smallPark extends Park7{
  override val m: Int = 1
}



/** --- Abstract Classes --- */
// Abstract classes on really used when giving blue print for something that's not going to change
// Use case for abstract classes is mainly for extending - providing a blueprint and safety net


/**  {}  */
// must use val/var
abstract class Park8{
  val coaster: Boolean
  val loop: Double
}
// make object - MUST give value for every parameter even if it has a default
// Safety net for developing code
new Park8 {
  override val coaster: Boolean = true
  override val loop: Double = 6.43
}

object HydePark extends Park8 {
  override val coaster: Boolean = false
  override val loop: Double = 0.0
}

/**  ()  */
// Differences: Can keep on one line, separate with comma
abstract class Park9(val coaster:Boolean, loop:Double)

// Make object -- Cannot instantiate an object with (), even with overrides
//new Park9() -- comment out to see ERROR

// Extend the class we can make an object
object AnotherPark extends Park9(coaster = true, loop = 5.2)

// What is accessible?
AnotherPark.coaster
AnotherPark.loop // No val/var declaration means you cannot access it

/**  () and {}  */

// anything in curly MUST have default parameter
// anything in round is a defined parameter & can be changed
class Park10(val coaster:Boolean){
  val loop:Double = 2.3
}
new Park10(coaster = true)

abstract class Park10(val coaster:Boolean){
  val loop:Double = 2.3
}
new Park10(coaster = true){
  override val loop: Double = 2.3}


/** --- TRAITS --- */

// traits all about inheritance - should always have defined vals
trait CandyFloss {
  val hasCandyFloss: Boolean
}

object AnotherPark2 extends CandyFloss {
  override val hasCandyFloss: Boolean = true
}

// trait with default value
trait CandyFloss2 {
  val hasCandyFloss: Boolean = true
}

object AnotherPark3 extends CandyFloss2 // Could still override as above

// Although can extend with unlimited traits, a lot of people who prefer abstract classes


/** --- Sealed keyword --- */
// Can only access in the same file - cannot import it
// Good for neat and contained code - safety within the same file
// Enables exhaustive matching 

sealed abstract class AbstractClassThatIsSealed
sealed trait SealedTrait
