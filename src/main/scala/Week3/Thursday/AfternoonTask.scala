package Week3.Thursday

object AfternoonTask extends App{


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
      else {println(postCodeSplit.length)
        Left(InvalidPostcodeError(message = s"$maybePostCode is an invalid Postcode"))}
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
  }


  trait GenericPostageError{def message:String}
  case class InvalidNameError(message:String) extends GenericPostageError
  case class InvalidPostcodeError(message:String) extends GenericPostageError
}
