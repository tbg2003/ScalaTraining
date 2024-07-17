
/** --- Pattern Matching --- */


//  matching a val
val weather:String = "cold"

if (weather.toLowerCase() == "cold") {
  println("take a coat")
} else if(weather.toLowerCase() == "rain"){
  println("take an umbrella")
} else{
  println("Take nothing")
}

weather.toLowerCase() match {
  case "cold" => println("take a coat")
  case "rain" => println("take an umbrella")
  case _ => println(s"$weather is not valid")
}


// Match of Int
val numOfWaterMelons: Int = 3
numOfWaterMelons match {
  case x if x <= 0 => println("Must be more than 0") // an 'if guard', be careful of ordering
  case 1 | 2 | 3 => println("Nice one, John can carry those")
  case 4 | 5 => println("You need a bag")
  case _ => println(s"$numOfWaterMelons is invalid")
}


// Matching of Objects

// Sealed can only be extended in the same file it is defined in
// sealed trait-> this will ensure exhaustive matching (don't need wild card)
// Will provide compilation warning if not exhaustive
sealed trait Temperature
object Cold extends Temperature
object Hot extends Temperature
object Mild extends Temperature

val weather: Temperature = Cold

weather match {
  case Cold => println("Brrrrr")
  case Hot => println("Aghhhhh")
  case Mild => println("Just right")
//  case _ => println("Not Valid") //-> this should never occur
}


// Matching Classes
abstract class Notification // Cannot be instantiated, must be extended

case class Email(sender:String, title:String, body:String) extends Notification
case class SMS(caller:String, message:String) extends Notification
case class VoiceRec(contactName:String, link:String) extends Notification


val notification:Notification = SMS("Mum", "Are you home?")

notification match {
  case Email(sender, title, body) => "Email from "+ sender
//   if guard for SMS must be above all SMS cases
  case SMS(caller, message) if caller.toLowerCase() == "mum" => "Text from "+caller+", don't ignore!!"
  case SMS(caller, message) => "Text from " + caller
  case VoiceRec(contactName, link) => "Voice recording from " + contactName
  case _ => "Some notification"
}


/** --- Pattern Matching Task --- */

sealed trait PizzaFlavour
object Spicy extends PizzaFlavour
object Weird extends PizzaFlavour
object Classic extends PizzaFlavour

val pizzaOrder: PizzaFlavour = Spicy
pizzaOrder match {
  case Spicy => "American Hot"
  case Weird => "Hawaiian"
  case Classic => "Pepperoni"
  case _ => "We don't have that sorry"
}

sealed trait PizzaSize
object Personal extends PizzaSize
object Small extends PizzaSize
object Medium extends PizzaSize
object Large extends PizzaSize


sealed trait PizzaCrust
object Classic extends PizzaCrust
object Italian extends PizzaCrust
object Stuffed extends PizzaCrust

abstract class PizzaOrder

case class Pizza(price:Double, crust: PizzaCrust, size: PizzaSize) extends PizzaOrder


