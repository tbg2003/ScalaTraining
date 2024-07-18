
/** --- Options --- */

// defined as Option[Type]
// Must wrap the value in a Some() or provide None

// Pizza Trait
trait Pizza {
  val size:Int
  val crust:Option[String]
}

// Types of Pizza
object Personal extends Pizza{
  override val size: Int = 7
  override val crust: Option[String] = None
}

object SmallClassic extends Pizza{
  override val size: Int = 9
  override val crust: Option[String] = Some("Classic")
}

object SmallItalian extends Pizza {
  override val size: Int = 9
  override val crust: Option[String] = Some("Italian")
}

// New Pizza order

 val pizzaOrder:Pizza = new Pizza{
  override val size: Int = 9
  override val crust: Option[String] = Some("Italian")
}

def whatCrust(pizza: Pizza): String = {
  pizza.crust.map(_.toLowerCase) match {
    case Some("Classic") => "Classic crust"
    case Some("Italian") => "Italian crust"
    case Some(x) => s"Sorry, we don't offer $x crust"
    case None => "Default Classic crust"
  }
}

whatCrust(pizzaOrder)

// .get method -> bad practise because .get on None doesn't return type string
val getCrust:String = pizzaOrder.crust.get

// .getOrElse -> good practise, can return default if funny business
val getOrElseCrust:String = pizzaOrder.crust.getOrElse("classic")

/** --- Options Task --- */

 sealed trait FillingType
case object Caramel extends FillingType
case object Nuts extends FillingType
case object Biscuit extends FillingType
case object Plain extends FillingType

case class ChocolateBar(filling:Option[FillingType]){

  def getFilling:String = {
    filling.getOrElse(Plain) match {
      case Caramel => "Filled with caramel"
      case Nuts => "Filled with nuts"
      case Biscuit => "Filled with biscuit"
      case Plain => "Just a plain old chocolate bar"
    }
  }
}

val KitKat:ChocolateBar = ChocolateBar(Some(Biscuit))
val Wispa:ChocolateBar = ChocolateBar(None)
val WispaGold:ChocolateBar = ChocolateBar(Some(Caramel))

KitKat.getFilling
Wispa.getFilling
WispaGold.getFilling