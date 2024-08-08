package Week4.Monday

import scala.annotation.tailrec

object Recursion extends App{


// Prevent messy terminal
  def printBreakMessage(message:String):Unit = {
    println()
    println("######################################################")
    println(s"\t$message\t")
    println("######################################################")
    println()
  }

  // Stackoverflow error -> too much memory on the stack

  /** --- Standard Recursion --- */

  def factorial(n:Int):Int = {
    if (n <= 1) n else n * factorial(n-1)
  }

  printBreakMessage("Standard Recursion - Factorial")
  println(factorial(5)) //--> Goes to 120 as expected
//  println(factorial(500000)) // --> results in "Exception in thread "main" java.lang.StackOverflowError"



/** --- Tail Recursion --- */
//  Similar to standard recursion but call stack reused, free up stack
  printBreakMessage("Tail Recursion - Factorial")


  def smartFactorial (n:Int):Int = {
//    First define helper method - give all things maybe needed to do recursion, but not in original method
//    x -> being the number and acc being the accumulator, can have multiple accumulators
    @tailrec
    def factorialHelper(x:Int, acc:Int):Int = {
      // All but final call defined in this helper method - recursion contained within
      if (x <= 1) acc
      else {
        factorialHelper(x-1, x * acc) // acc holds the running total, one val instead of the whole call stack :)
      }
    }

    factorialHelper(n, 1)
  }

  println(smartFactorial(5))
  println(smartFactorial(500000)) // Get value of 0 instead of error -> output is lng instead of int



  printBreakMessage("Attempt at Fibonacci")

  def fibonacci(x:Int):Seq[Int] = {
    @tailrec
    def fibonacciHelper(x:Int, acc:Seq[Int]=Seq(1,1)):Seq[Int] = {
      if (x<=2)acc
      else{
        val lastX = acc.last
        val secondLastX = acc(acc.length-2)
        fibonacciHelper(x-1, acc++Seq(lastX+secondLastX))
      }
    }
    fibonacciHelper(x)
  }
  println(fibonacci(8))



  printBreakMessage("Tail recursion, no helper - Concatenate Words ")

  @tailrec
  def concatenateWords(aWord:String, n:Int, acc:String):String = {
    if (n<=0) acc
    else{
      concatenateWords(aWord, n-1, aWord+acc)
    }
  }
  println(concatenateWords("Thomas", 3, ""))




  printBreakMessage("Task - no. Elements in List of String")


  println()
  println("Part A)")

  def numOfElementsInString(stringList:List[String]):Int = {
    if (stringList.isEmpty) 0
    else{
      1 + numOfElementsInString(stringList.tail)
    }
  }
  println(numOfElementsInString(List("hello", "my", "name", "is", "Tom")))
  println(numOfElementsInString(List()))


  println()
  println("Part B)")

  def numOfElementsInString2(stringList:List[String]):Int = {
    @tailrec
    def helperNumOfElements(stringList:List[String], x:Int=0):Int = {
      if (stringList.isEmpty) x
      else{
        helperNumOfElements(stringList.tail, x+1)
      }
    }
    helperNumOfElements(stringList)
  }
  println(numOfElementsInString2(List("hello", "my", "name", "is", "Tom")))
  println(numOfElementsInString2(List()))


  println()
  println("Part C)")

  def numOfNonEmptyElements(stringList:List[String]):Int = {
    @tailrec
    def helperNumOfElements(stringList:List[String], x:Int=0):Int = {
      stringList match {
        case Nil => x
        case ::(empty, tail) if empty == "" => helperNumOfElements(tail, x) // doesn't count empty strings
        case _::tail => helperNumOfElements(tail, x+1) // Another notation, either should be fine? Maybe ask for bets practise
      }
    }
    helperNumOfElements(stringList)
  }

  println(numOfNonEmptyElements(List("1", "2", "3")))
  println(numOfNonEmptyElements(List("1")))
  println(numOfNonEmptyElements(List()))
  println(numOfNonEmptyElements(List("")))



  /** --- Enumeration, Case Objects and Recursion --- */

printBreakMessage("Recursion With Enumeration and Case Objects")


  /** --- Recursion with Enumeration --- */
  //  Define enum list
  object DayOfWeek extends Enumeration{
    type DayOfWeek = Value
    val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value
  }

//  Convert list of enum to string

  def convertEnum(dayOfWeek: List[DayOfWeek.Value]):String = {
    @tailrec
    def enumListHelper(enumDays: List[DayOfWeek.Value], acc:String=""):String = {
      enumDays match {
        case Nil => acc // check fail first to reduce comp. time
        case ::(head, rest) =>
          val aesthetic = if(acc.isEmpty) "" else ", " // to get string in readable format
          enumListHelper(rest, acc + aesthetic + head.toString)
      }
    }
    enumListHelper(dayOfWeek)
  }

  println("Recursion and Enumeration")
  println(convertEnum(DayOfWeek.values.toList))


  /** --- Recursion with Case Objects --- */

  // Define case objects
  sealed trait DaysOfWeek
  case object Monday extends DaysOfWeek
  case object Tuesday extends DaysOfWeek
  case object Wednesday extends DaysOfWeek
  case object Thursday extends DaysOfWeek
  case object Friday extends DaysOfWeek
  case object Saturday extends DaysOfWeek
  case object Sunday extends DaysOfWeek

  def objectsToString(daysList: List[DaysOfWeek]):String = {
    @tailrec
    def objectToStringHelper(days:List[DaysOfWeek], acc:String = ""):String = {
      days match {
        case Nil => acc
        case ::(head, next) =>
          val aesthetic = if(acc.isEmpty)"" else ", "
          objectToStringHelper(next, acc + aesthetic + head.toString)
      }
    }
    objectToStringHelper(daysList)
  }
  println("Recursion and Case Objects")
  println(objectsToString(List(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday)))


  /** --- Task --- */

  printBreakMessage("Task")

  sealed trait Fruit
  case object Apple extends Fruit
  case object Orange extends Fruit
  case object Banana extends Fruit
  case object Mango extends Fruit
  case object Grape extends Fruit

  val items:List[Fruit] = List(Apple, Orange, Banana, Apple, Mango, Apple, Grape, Banana)

  def countFruit(fruits:List[Fruit], targetFruit:Fruit):Int = {
   @tailrec
    def countFruitHelper(fruitList:List[Fruit], acc:Int = 0):Int = {
      fruitList match {
        case Nil => acc
        case ::(head, tail) if head == targetFruit => countFruitHelper(tail, acc+1)
        case ::(_, tail) => countFruitHelper(tail, acc)
      }
    }
    countFruitHelper(fruits)
  }

  println(countFruit(items, Apple))
}

