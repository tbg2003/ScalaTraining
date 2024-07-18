package Week3.Thursday

import Week3.Thursday.Task._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TaskSpec extends AnyWordSpec with Matchers{

  "creditScoreCheck" should{
    "return Right" when{
      "credit score is 550" in {
        val validCreditScore = 550
        val result = MortgageRequest.creditScoreCheck(validCreditScore)
        result shouldBe Right(550)
      }
      "credit score is over 550" in {
        val validCreditScore = 560
        val result = MortgageRequest.creditScoreCheck(validCreditScore)
        result shouldBe Right(560)
      }
    }
    "return Left" when{
      "credit score is under 550" in {
        val invalidCreditScore = 540
        val result = MortgageRequest.creditScoreCheck(invalidCreditScore)
        result shouldBe Left("Credit score too low")
      }
    }
  }

  "depositCheck" should {
    "return Right" when {
      "Deposit is 10% of the property price" in {
        val validDeposit = 10.0
        val propertyPrice = 100.0
        val result = MortgageRequest.depositCheck(validDeposit, propertyPrice)
        result shouldBe Right(10.0)
      }
      "return Right" when {
        "Deposit above 10% of the property price" in {
          val validDeposit = 20.0
          val propertyPrice = 100.0
          val result = MortgageRequest.depositCheck(validDeposit, propertyPrice)
          result shouldBe Right(20.0)
        }
      }
    }
    "return Left" when {
      "Deposit is under 10% of the property price" in {
        val invalidDeposit = 5.0
        val propertyPrice = 100.0
        val result = MortgageRequest.depositCheck(invalidDeposit, propertyPrice)
        result shouldBe Left("Deposit too low")
      }
    }
  }

  "mortgageAmount" should {
    "return Right" when {
      "deposit and credit score are both valid" in {
        val validDeposit = 10.0
        val validCreditScore = 580
        val priceOfProperty = 100.0
        val annualSalary = 500.0
        val result = MortgageRequest.mortgageAmount(priceOfProperty, annualSalary, validDeposit, validCreditScore)
        result shouldBe Right(2000.0)
      }
    }
    "return Left" when {
      "deposit is invalid and credit score is valid" in {
        val invalidDeposit = 5.0
        val validCreditScore = 580
        val priceOfProperty = 100.0
        val annualSalary = 500.0
        val result = MortgageRequest.mortgageAmount(priceOfProperty, annualSalary, invalidDeposit, validCreditScore)
        result shouldBe Left("Deposit too low")
      }
      "deposit is valid and credit score is invalid" in {
        val validDeposit = 10.0
        val invalidCreditScore = 400
        val priceOfProperty = 100.0
        val annualSalary = 500.0
        val result = MortgageRequest.mortgageAmount(priceOfProperty, annualSalary, validDeposit, invalidCreditScore)
        result shouldBe Left("Credit score too low")
      }
      "deposit is invalid and credit score is invalid" in {
        val invalidDeposit = 5.0
        val invalidCreditScore = 400
        val priceOfProperty = 100.0
        val annualSalary = 500.0
        val result = MortgageRequest.mortgageAmount(priceOfProperty, annualSalary, invalidDeposit, invalidCreditScore)
        result shouldBe Left("Credit score too low")
      }
    }
  }
}
