// ###################################
// #  Object Orientated Programming  #
// ###################################

/** ---- Functions and Methods ---- */
/* Methods:
are a single type of function
use the keyword 'def'
They belong to a class
Can be overridden
Cannot be anonymous
 */

// Anonymous Function
(number:Int) => number + 1
// Named Function
val addOne = (number:Int) => number+1
// Method (a type of function)
def addOneMethod() = (number:Int) => number+1
// Override
def addOneMethod() = (number:Int) => number+2

// Example 1
def makeCupOfTea(numSugars:Int, addMilk:Boolean): String = {
  val typeOfTea:String = if(addMilk) "white" else "black"
  val sugars:String = if(numSugars==0) "no" else numSugars.toString
  s"You have made a $typeOfTea with $sugars spoons of sugar"
}

makeCupOfTea(numSugars = 0, addMilk = false)

// Example 2
val vat: Double = 1.2
def priceWithVat(price:Double): Double = {
  price * vat
}

priceWithVat(5)

// Method within a class

class Number(num:Int){
  def addition(addNumber: Int): Int = {
    num + addNumber
  }
}

val newNumberClass = new Number(7)
newNumberClass.addition(3)


// Task 1
def getBigVal(numOne:Int, numTwo:Int):String = {
  if(numOne > numTwo) "first"
  else if (numOne < numTwo) "second"
  else if (numOne == numTwo) "same"
  else "error"
}

getBigVal(2,1)
getBigVal(1,2)
getBigVal(2,2)



// Task 2
def getBigName(numOne:Int, numTwo:Int):Int = {
  if(numOne > numTwo) numOne
  else if (numOne < numTwo) numTwo
  else if (numOne == numTwo) 0
  else -1
}

def nameLength(firstName:String, lastName:String): Int = {
  getBigName(firstName.length, lastName.length)
}

nameLength("Tom", "Hiddleston")
nameLength("Elizabeth", "Olsen")
nameLength("Chris", "Pratt")

/** ---- Referential Programming ---- */
//  the output of the method is based solely on the input

/** ---- Pure and Impure Functions ---- */
//  PURE:
//  given the same inputs the output will always be the same
val sentence: String = ""
def append(word:String): String = {
  sentence + "" + word 
}
append("oi")
append("oi")

// IMPURE:
// given the same inputs the output will be different if passed multiple times
// Usually because of a VAR
var sentence: String = " "
def append(word:String): String = {
  sentence = sentence + " " + word
  sentence.trim()
}
append("oi")
append("oi")





