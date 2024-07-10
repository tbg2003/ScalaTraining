package Week2.Wednesday

import org.scalatest.FlatSpec

class TwoNumbersSpec extends FlatSpec {
  val sameXAndY = new TwoNumbers(x = 3, y = 3)
  val bigXSmallY = new TwoNumbers(x = 3, y = 1)
  val smallXBigY = new TwoNumbers(x = 1, y = 3)
  val xMultipleY = new TwoNumbers(x = 6, y = 3)
  val xNotMultipleY = new TwoNumbers(x = 7, y = 3)
  
  "add" should "add numbers" in {
    assert(sameXAndY.add == 6)
  }
  "subtractYfromX" should "subtract y from x" in {
    assert(sameXAndY.subtractFromX == 0)
    assert(bigXSmallY.subtractFromX == 2)
    assert(smallXBigY.subtractFromX == -2)
  }

  "multiply" should "multiply numbers" in {
    assert(sameXAndY.multiply == 9)
  }

  "divideXbyY" should "divide x by y" in {
    assert(sameXAndY.divideXByY == 1.0)
    assert(bigXSmallY.divideXByY == 3.0)
    assert(smallXBigY.divideXByY == 1/3)
  }

  "isMultipleOfY" should "x is a multiple of y" in {
    assert(xMultipleY.isMultipleOfY())
    assert(!xNotMultipleY.isMultipleOfY())
  }

}
