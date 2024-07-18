package Week3.Thursday

import java.time.LocalDate

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


  /** --- For Comprehension WITH Eithers --- */
  //   Use these because they fail fast. As soon as one condition is not met

  case class User (userName:String, password:String, dateOfBirth:LocalDate)


  case class ValidatedUser(userName:String, password:String, dateOfBirth:LocalDate)

  // Validate the username
  def validateUsername(username:String):Either[String, String] = {
    if (username.length < 12) Left("username must be at least 12 characters")
    else Right(username)
  }
  // Validate the password
  def validatePassword(password:String):Either[String, String] = {
    if (password.toUpperCase == password || password.toLowerCase == password) Left("Password must be a mixture of Upper and Lower case")
    else Right(password)
  }

  // Validate the age
  def validateAge(dateOfBirth:LocalDate):Either[String, LocalDate] = {
    if (dateOfBirth.plusYears(18).isAfter(LocalDate.now())) Left("User must be 18 years old")
    else Right(dateOfBirth)
  }
  // Validate all 3 and validate whole user as a for comprehension
  def validateUser(user:User):Either[String, ValidatedUser] = {
    for {
      username <- validateUsername(user.userName)
      password <- validatePassword(user.password)
      dateOfBirth <- validateAge(user.dateOfBirth)
      // create case class for validated user
      createValidatedUser = ValidatedUser(userName = username, password = password, dateOfBirth = dateOfBirth)
    } yield createValidatedUser // Yielding case class - only if all methods above hit the right
    // if any methods hit the left, the exit and return their Left
  }

  val usernameBad = User("Tom", "Tom", LocalDate.of(2003, 4, 7))
  val passwordBad = User("TomBurtGray2003", "tom", LocalDate.of(2003, 4, 7))
  val dateOfBirthBad = User("TomBurtGray2003", "Tom", LocalDate.of(2023, 4, 7))
  val goodUser = User("TomBurtGray2003", "Tom", LocalDate.of(2003, 4, 7))

  println(validateUser(usernameBad)) // expect Left for user name being too short
  println(validateUser(passwordBad)) // expect Left for password must be mix upper and lower
  println(validateUser(dateOfBirthBad)) // expect Left for not being 18
  println(validateUser(goodUser)) // expect Right for validated user
  
}
