package Week9

object Variance extends App{


/** ---- Covariance [+T] ---- */
  abstract class Coffee{
    def name: String
  }

  case class Standard(name:String) extends Coffee
  case class Fancy(name:String) extends Coffee

  // cmd + hover over list : we can see of type +A --> covariance
  def coffeeMenu(coffees:List[Coffee]):String = {
    coffees.foreach{
      coffee => println(coffee.name)
    }.toString
  }

  val standardCoffeeMenu:List[Standard] = List(Standard("latte"), Standard("Americano"), Standard("Cappuccino"))
  val fancyCoffeeMenu:List[Fancy] = List(Fancy("Mocha"), Fancy("Iced Coffee"), Fancy("Some Pumpkin Thing"))

  coffeeMenu(standardCoffeeMenu)
  coffeeMenu(fancyCoffeeMenu)




  /** ---- Contravariance [-T] ---- */
  // opposite of covariance - must explicitly define the type in an overarching abstract class
  abstract class SummerActivity[-T]{
    def printValue(value:T):String
  }

  // super-class
  abstract class Festival{
    def name:String
  }

  // sub-class of Festival
  case class MusicFestival(name:String) extends Festival

  // sub-class of SummerActivity
  class Glastonbury extends SummerActivity[Festival]{
    override def printValue(festival: Festival): String = festival.name
  } // This class can handle any type of festival

  class Reading extends SummerActivity[MusicFestival]{
    override def printValue(musicFestival: MusicFestival): String = musicFestival.name
  } // This class can only handle Music festivals, no other type of festivals -> more specific than glastonbury class
  // Festival class out of reach due to contravariance

  /**
   * SummerActivity[Festival] is now the super type
   * SummerActivity[MusicFestival] is now the sub-type
   */


  val newMusicFestival:MusicFestival = MusicFestival("New Music Festival")


  def printNewFestival(summerActivity:SummerActivity[MusicFestival]):String = {
    summerActivity.printValue(newMusicFestival)
  }

  val festival1: SummerActivity[Festival] = new Glastonbury // Valid, matching types
  val festival2: SummerActivity[MusicFestival] = new Glastonbury // Valid, super into sub
  // due to contravariance, its compatible with the sub-type SummerActivity[MusicFestival] -> can go super down
  //val festival3: SummerActivity[Festival] = new Reading//Invalid, can't go sub up
  val festival4: SummerActivity[MusicFestival] = new Reading // Valid, matching types

  println(printNewFestival(festival1))
  println(printNewFestival(festival2))
  println(printNewFestival(festival4))



  /** ---- Invariance [T] ---- */

  class Box[T] (var contentOfBox: T) // generic class that holds a value of type T in its contents


  abstract class Fruit{
    def variety:String
  }

  case class Apple(variety:String) extends Fruit
  case class Orange(variety:String) extends Fruit
  // NOTE: both apple and fruit are sub-types of Fruit

  val appleBox: Box[Apple] = new Box[Apple](Apple("Gala"))
  //val fruitBox: Box[Fruit] = appleBox
}
