package Week3.Thursday

object Task extends App{

  case class MortgageRequest(priceOfProperty:Double, annualSalary:Double, deposit:Double, creditScore:Int)

  case object MortgageRequest{
    def creditScoreCheck(creditScore:Int):Either[String, Int] = {
      if (creditScore >= 550) Right(creditScore)
      else Left("Credit score too low")
    }
    def depositCheck(deposit:Double, priceOfProperty:Double):Either[String, Double] = {
      val minDeposit:Double = priceOfProperty*0.1
      if (deposit >= minDeposit) Right(deposit)
      else Left("Deposit too low")
    }
    def mortgageAmount(priceOfProperty:Double, annualSalary:Double, deposit:Double, creditScore:Int):Either[String, Double] = {
      for {
        validCreditScore <- creditScoreCheck(creditScore)
        validDeposit <- depositCheck(deposit, priceOfProperty)
        mortgageAmount = 4 * annualSalary
      } yield mortgageAmount
    }
  }
}
