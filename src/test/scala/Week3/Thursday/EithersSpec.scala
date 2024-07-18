package Week3.Thursday

import Week3.Thursday.Eithers._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EithersSpec extends AnyWordSpec with Matchers{

  "isOdd" should{
    "return a Right" when{
      "the input number is odd" in {
        val result = isOdd(3)
        result shouldBe Right("3 is odd")
      }
      "the number is odd and large" in {
        val result = isOdd(10000001)
        result shouldBe Right("10000001 is odd")
      }
    }

    "return a Left" when{
      "the number is even" in {
        val result = isOdd(4)
        result shouldBe Left(IsOddError)
      }
    }
  }
}
