package Week2.Wednesday

import org.scalatest.flatspec.AnyFlatSpec

class FizzBuzzSpec extends AnyFlatSpec {
  val divByThree = new FizzBuzz(x = 6)
  val divByFive = new FizzBuzz(x = 10)
  val divByThreeFive = new FizzBuzz(x = 15)
  val prime = new FizzBuzz(x = 13)
  val zero = new FizzBuzz(x = 0)

  "divisiblyBy3" should "check x is divisible by 3" in {
    assert(divByThree.fizzBuzz == "Fizz")
    assert(divByFive.fizzBuzz != "Fizz")
    assert(divByThreeFive.fizzBuzz != "Fizz")
    assert(prime.fizzBuzz != "Fizz")
    assert(zero.fizzBuzz != "Fizz")
  }
  "divisiblyBy5" should "check x is divisible by 5" in {
    assert(divByFive.fizzBuzz == "Buzz")
    assert(divByThree.fizzBuzz == "Buzz")
    assert(divByThreeFive.fizzBuzz != "Buzz")
    assert(prime.fizzBuzz != "Buzz")
    assert(zero.fizzBuzz != "Buzz")
  }
  "divisiblyBy3And5" should "check x is divisible by 3 and 5" in {
    assert(divByThreeFive.fizzBuzz == "FizzBuzz")
    assert(divByFive.fizzBuzz != "FizzBuzz")
    assert(divByFive.fizzBuzz != "FizzBuzz")
    assert(prime.fizzBuzz != "FizzBuzz")
    assert(zero.fizzBuzz == "FizzBuzz")
  }
}
