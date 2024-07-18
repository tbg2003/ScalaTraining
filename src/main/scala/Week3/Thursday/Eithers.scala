package Week3.Thursday

object Eithers extends App{

  /** --- Either, a type similar to Option --- */
  // Either[A, B] where:
  //  - Left [A] --> is usually the error case
  //  - Right [B] --> is usually the success case

  def isEven(x:Int): Either[String, String] = {
    if (x % 2 == 0) Right(s"$x is even") else Left(s"$x is odd")
  }

  println(isEven(4))
  println(isEven(3))

  //  Custom Error - could be trait with curly{} instead
  class NewError(val errorCode:Int, val errorMessage:String) extends Exception

  case object IsOddError extends NewError(errorCode = 400, errorMessage = "Bad Request in isOdd Method")


def isOdd(x:Int): Either[NewError, String] = {
  if (x % 2 != 0) Right(s"$x is odd") else Left(IsOddError)
}

  println(isOdd(4)) // Expect Error
  println(isOdd(3)) // Expect message


  /** --- Ways of handling Eithers --- */

  // Try / Catch

  def plusOne(x:String): Either[String, Int] = {
    try{
      Right(x.toInt)
    } catch {
      case e: Exception => Left(s"failed due to $e") // wildcard of try/catch
    }
  }
  println(plusOne("1")) // Expect Right
//  println(plusOne("one")) // Expect Left
  plusOne("3").map(num => num +1)
//  plusOne("three").map(num => num +1)


  // Try / Catch

  def plusOneMatch():Unit = {
    plusOne("one") match {
      case Left(e) => println(s"Left: $e") // comes first in case there is error, spotted sooner
      case Right(x) => println(s"Right: $x")
    }
  }
  println(plusOneMatch())





}
