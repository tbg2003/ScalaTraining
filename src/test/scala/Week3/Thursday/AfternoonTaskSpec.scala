package Week3.Thursday
import Week3.Thursday.AfternoonTask._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AfternoonTaskSpec extends AnyWordSpec with Matchers{

  "nameOrError" should {
    "return a Left" when {
      "the name contains a digit" in {
        val invalidName = "Thom4s"
        val result = Name.nameOrError(invalidName)
        result shouldBe Left(InvalidNameError("The name Thom4s is invalid, it contains a digit"))
      }
    }
    "return a Right" when {
      "the name does not contain any digit" in {
        val validName = "Thomas"
        val result = Name.nameOrError(validName)
        result shouldBe Right(Name("Thomas"))
      }
    }
  }

  "postcodeOrError" should {
    "return a Left" when {
      "postcode is not two strings separated by whitespace" in {
        val invalidPostcode = "CB23"
        val result = Postcode.postcodeOrError(invalidPostcode)
        result shouldBe Left(InvalidPostcodeError("CB23 is an invalid Postcode"))
      }
    }
    "return a Right" when {
      "postcode is two strings separated by whitespace" in {
        val validPostcode = "CB23 1JE"
        val result = Postcode.postcodeOrError(validPostcode)
        result shouldBe Right(Postcode("CB23 1JE"))
      }
      "postcode is two strings separated by large whitespace" in {
        val validPostcode = "CB23    1JE"
        val result = Postcode.postcodeOrError(validPostcode)
        result shouldBe Right(Postcode("CB23 1JE"))
      }
      "postcode is two strings separated by whitespace with leading white space" in {
        val validPostcode = "   CB23 1JE"
        val result = Postcode.postcodeOrError(validPostcode)
        result shouldBe Right(Postcode("CB23 1JE"))
      }
      "postcode is two strings separated by whitespace with trailing white space" in {
        val validPostcode = "CB23 1JE   "
        val result = Postcode.postcodeOrError(validPostcode)
        result shouldBe Right(Postcode("CB23 1JE"))
      }
    }
  }

  "letterOrError" should {
    "return a Left" when {
      "name is invalid and postcode is valid" in {
        val invalidName = "Thom4s"
        val validPostCode = "Cb23 1JE"
        val result = Letter.letterOrError(invalidName, validPostCode)
        result shouldBe Left(InvalidNameError("The name Thom4s is invalid, it contains a digit"))
      }
      "name is valid and postcode is invalid" in {
        val validName = "Thomas"
        val invalidPostCode = "CB23"
        val result = Letter.letterOrError(validName, invalidPostCode)
        result shouldBe Left(InvalidPostcodeError("CB23 is an invalid Postcode"))
      }
      "name is invalid and postcode is invalid" in {
        val invalidName = "Thom4s"
        val invalidPostCode = "CB23"
        val result = Letter.letterOrError(invalidName, invalidPostCode)
        result shouldBe Left(InvalidNameError("The name Thom4s is invalid, it contains a digit"))
      }
    }
    "returns a Right" when {
      "name and postcode are valid" in {
        val validName = "Thomas"
        val validPostCode = "CB23 1JE"
        val result = Letter.letterOrError(validName, validPostCode)
        result shouldBe Right(Letter(Name("Thomas"), Postcode("CB23 1JE")))
      }
    }
  }
}
