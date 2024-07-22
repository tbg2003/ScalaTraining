package Week4.Monday

import Week4.Monday.Recursion._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RecursionSpec extends AnyWordSpec with Matchers{

  "concatenateWords" should {
    "return the acc when n is negative" in {
        val methodCall = Recursion.concatenateWords("hello", -1, "")
        val expected = ""
        methodCall shouldEqual expected
      }

      "return the acc when n is 0" in {
        Recursion.concatenateWords("hello", 0, "") shouldBe ""
      }

    "return a concatenated string of 'hello' three times if n is three" in{
      Recursion.concatenateWords("hello ", 3, "") shouldEqual "hello hello hello "
    }

    "concatenate an empty string" in{
      Recursion.concatenateWords("",3,"") shouldEqual ""
    }

    "concatenate words when the acc isn't empty" in {
      Recursion.concatenateWords("hello ", 3, "appendMe") shouldEqual "hello hello hello appendMe"
    }
  }
}
