/** ---- MVP ---- */

// 1a
def addOne(x:Int):Int = {
  x + 1
}

def applyTwice(x:Int, function:Int=>Int):Int = {
  function(function(x))
}



// 1b
def addition(x:Double, y:Double):Double = x + y
def subtraction(x:Double, y:Double):Double = x - y
def multiplication(x:Double, y:Double):Double = x * y
def division(x:Double, y:Double):Double = x / y

def mathFunc(x:Double, y:Double, operation:(Double,Double)=>Double):Double = {
  operation(x,y)
}



// 2a
def convertToUpperCase(string: String):String = string.toUpperCase
def convertToLowerCase(string: String):String = string.toLowerCase

def convertString(makeStringUpperCase:Boolean):String => String = {
  if (makeStringUpperCase) convertToUpperCase else convertToLowerCase
}

val testString = "cOnVeRtMe"
convertString(true)(testString)
convertString(false)(testString)



// 2b
def calculate(operation: String):(Double,Double) => Double = {
  operation.trim match {
    case "add" => addition
    case "subtract" => subtraction
    case "multiply" => multiplication
    case "divide" => division
  }
}
val add = calculate("add")
val subtract = calculate("subtract")
val multiply = calculate("multiply")
val divide = calculate("divide")
//val invalid = calculate("invalid")

val x:Double = 10.0
val y:Double = 2.0

println(s"adding $x to $y gives: ${add(x,y)}")
println(s"subtracting $y from $x gives: ${subtract(x,y)}")
println(s"multiplying $x and $y gives: ${multiply(x,y)}")
println(s"dividing $x by $y gives: ${divide(x,y)}")




/** ---- Extension & Research ---- */

// Task 1

def max(x:Double, y:Double):Double = x max y
def max2(x:Double, y:Double):Double = Math.max(x,y)
def maxListNum(nums: List[Double]):Double = nums.reduce((x,y) => x max y)
def maxListNum2(nums: List[Double]):Double = nums.max
def applyFunc(func:Int=>Int, int: Int):Int = func(int)

def double(x:Int):Int = x*2
def square(x:Int):Int = x*x

val number:Int = 5
println(applyFunc(double, number))
println(applyFunc(square, number))



def power(exponent:Int):Int => Int = {
 base => Math.pow(base, exponent).toInt
}


def squareX(base:Int):Int = power(2)(base)
println(square(6))










