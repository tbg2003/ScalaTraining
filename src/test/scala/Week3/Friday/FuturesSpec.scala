package Week3.Friday

import Week3.Friday.Futures.{addition, additionInTheFuture, fetchIsOddError}
import Week3.Thursday.Eithers.IsOddError
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.AnyWordSpec

class FuturesSpec extends AnyWordSpec with Matchers with ScalaFutures{

  implicit val defaultWaitTime:PatienceConfig = PatienceConfig(timeout = Span(3, Seconds), interval = Span(500, Millis))

  "addition method" should {
    "add two ints" in {
      addition(1, 2) shouldEqual 3
    }
  }

/** UNIT TESTING THE FUTURE */
//   When ready
  "additionInTheFuture" should{
    "eventually return the sum" in {
      whenReady(additionInTheFuture) {
        result => result shouldEqual 3
      }
    }
  }


  "fetchIsOddError" should {
    "return a Right" when{
      "Future succeeds and x is odd" in {
        val x:Int = 5
        whenReady(fetchIsOddError(x)){
          result => result shouldEqual Right(s"$x is odd")
        }
      }
    }
    "return a Left" when{
      "Future succeeds and x is even" in {
        val x:Int = 6
        whenReady(fetchIsOddError(x)){
          result => result shouldEqual Left(IsOddError)
        }
      }
    }
    "return an Exception" when{
      "Future Fails" in{
        val x:Int = 7
        whenReady(fetchIsOddError(x)){
          result => result shouldEqual Left(IsOddError)
        }
      }
    }
  }

}
