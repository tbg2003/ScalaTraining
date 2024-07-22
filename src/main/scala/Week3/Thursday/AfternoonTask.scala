package Week3.Thursday


import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}

import scala.util.{Failure, Success, Try}

object AfternoonTask extends App{


  implicit val ec: ExecutionContext = ExecutionContext.global
//  Don't include this as tests as tests should be very independent and separate to the rest of the code
//   They should run the code in isolation and not interfere with anything global


  case class Name(value:String)

  case object Name{

    def nameOrError(maybeName:String):Either[InvalidNameError, Name] = {
     if (maybeName.exists(_.isDigit)) Left(InvalidNameError(s"The name $maybeName is invalid, it contains a digit"))
     else Right(Name(maybeName))
    }
  }


  case class Postcode(value:String)

  case object Postcode{

    def postcodeOrError(maybePostCode:String):Either[InvalidPostcodeError, Postcode] = {
      val postCodeSplit = maybePostCode.strip().split(" ").filter(_ != "")
      if (postCodeSplit.length == 2) Right(Postcode(postCodeSplit(0) + " " + postCodeSplit(1)))
      else Left(InvalidPostcodeError(message = s"$maybePostCode is an invalid Postcode"))
    }
  }


  case class Letter(name:Name, postcode:Postcode)

  case object Letter{

    def letterOrError(maybeName:String, maybePostCode:String):Either[GenericPostageError, Letter]= {
      for {
        name <- Name.nameOrError(maybeName)
        postcode <- Postcode.postcodeOrError(maybePostCode)
        letter = Letter(name, postcode)

      } yield letter
    }

    def fetchLetter:Future[Letter] = Future{
      Thread.sleep(1500)
      Letter(Name("Thomas"), Postcode("CB23 1JE"))
    }

    def fetchLetterOrError(name:String, postcode:String):Future[Either[GenericPostageError, Letter]] = Future{
      Thread.sleep(1500)
      letterOrError(name, postcode)
    }
  }


  trait GenericPostageError{def message:String}
  case class InvalidNameError(message:String) extends GenericPostageError
  case class InvalidPostcodeError(message:String) extends GenericPostageError

  val name:String = "Thomas"
  val postcode:String = "CB23"
  val eventualLetterOrError:Future[Either[GenericPostageError, Letter]] = Letter.fetchLetterOrError(name, postcode)

//  Await.ready(eventualLetterOrError, 2.seconds)
//
//  eventualLetterOrError.value match {
//    case Some(Success(either)) =>
//      either match {
//        case Left(postageError) => println(s"Error: ${postageError.message}")
//        case Right(foundLetter) => println(s"Found letter: $foundLetter")
//      }
//
//    case Some(Failure(exception)) => println(s"Failed with Exception: $exception")
//
//    case None => println("The future did not complete")
//  }

//  Trying to differentiate between incomplete valid and invalid
  val eventualLetterOrError2 = Try(Await.ready(eventualLetterOrError, 1.seconds))
  eventualLetterOrError2 match {
    case Failure(exception) => println(s"Failed with exception 1: $exception") // Incomplete Invalid & Valid --> No matter what you cant separate the invalid and valid if incomplete
    case Success(future) =>
      future.value match {
        case Some(Success(either)) =>
          either match {
            case Left(postageError) => println(s"Error: ${postageError.message}") // Complete Invalid
            case Right(foundLetter) => println(s"Found letter: $foundLetter") // Complete Valid
          }

        case Some(Failure(exception)) => println(s"Failed with Exception 2: $exception")

        case None => println("The Future did not complete")
      }
    case Failure(exception) =>
      println(s"Await timed out: $exception")
  }

}

