package Week4.Monday

import Week4.Monday.AfternoonTask._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AfternoonTaskSpec extends AnyWordSpec with Matchers{

  "fibonacciCalc method" should {
    "return a Left" when {
      "The value of n is negative" in {
        fibonacciCalc(-1) shouldEqual Left("Error: input must be positive, -1 < 0")
      }
    }

    "return a Right" when {

      "The value of n is greater than 2" in {
        fibonacciCalc(5) shouldEqual Right(Seq(1, 1, 2, 3, 5))
      }
      // Edge Cases
      "The value of n is zero" in {
        fibonacciCalc(0) shouldEqual Right(Seq())
      }
      "The value of n is 1" in {
        fibonacciCalc(1) shouldEqual Right(Seq(1))
      }
      "The value of n is 2" in {
        fibonacciCalc(2) shouldEqual Right(Seq(1, 1))
      }
    }
  }
}
