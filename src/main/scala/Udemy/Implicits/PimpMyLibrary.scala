package Udemy.Implicits

import scala.language.implicitConversions

object PimpMyLibrary extends App {

  implicit class RichInt(val value: Int) extends AnyVal {
    def isEven: Boolean = value % 2 == 0
    def sqrt: Double = Math.sqrt(value)

    def times(fucntion: () => Unit): Unit = {
      def timesAux(n: Int): Unit = {
        if(n <= 0) ()
        else {
          fucntion()
          timesAux(n - 1)
        }
      }
      timesAux(value)
    }
    def *[T](list: List[T]): List[T] = {
      def concatenate(n: Int): List[T] = {
        if(n <= 0) List()
        else concatenate(n-1) ++ list
      }
      concatenate(value)
    }
  }

  implicit class RicherInt(richInt: RichInt) {
    def isOdd: Boolean = richInt.value % 2 != 0
  }

  new RichInt(42).sqrt

  // type enrichment -> pimping
  42.isEven // compiles to -> new RichInt(42).isEven
  // examples of implicit classes in use
  1 to 10
  import scala.concurrent.duration._
  3.seconds

  // compiler doesn't do multiple implicit searches
  42.isEven // compiles
  // 42.isOdd // Doesn't compile

  /**
   * --- Enrich String Class ---
   * - asInt
   * - encrypt (using Caesar)
   *
   * --- Enrich Int Class ---
   * - times(function)
   *    3.times(() => ...)
   *  - *
   *    3 * List(1, 2) => List(1,2,1,2,1,2)
   */

  implicit class RichString(val string: String) extends AnyVal {
    def asInt: Int = Integer.valueOf(string)
    def encrypt(cypherDist:Int = 2): String = string map (char => (char.toInt + cypherDist).toChar)
  }

  println("3".asInt + 4)
  println("John".encrypt())

  println(3 * List(1,2))
  3.times(() => println("hello"))


  // implicit conversions --> "3" / 4
  implicit def stringToInt(string: String):Int = Integer.valueOf(string)

  println("6" / 2)

  // equivalent: implicit class RichAltInt(value: Int)
  class RichAltInt(value: Int)
  implicit def enrich(value: Int): RichAltInt = new RichAltInt(value)


  // danger zone
  implicit def intToBoolean(i: Int): Boolean = i == 1

  val aConditionedValue = if(3) "OK" else "Something wrong"

  println(aConditionedValue)
}
