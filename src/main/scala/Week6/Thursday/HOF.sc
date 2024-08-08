


/** --- Higher Order Functions --- */


// What does it mean to be a first class citizen?



// 1. assign functions to vals
val addition:(Int, Int) => Int = (x,y) => x + y
val subtract:(Int, Int) => Int = (x,y) => x - y
val multiply:(Int, Int) => Int = (x,y) => x * y
addition(1, 2)

// 2. Store functions in collections
val funcsInList = List(
  (a:Int, b:Int) => a + b,
  (a:Int, b:Int) => a * b,
  (a:Int, b:Int) => a - b
)
// accessing each element
funcsInList.head(1,2)
funcsInList(1)(1,2)
funcsInList.last(1,2)


/** IMPORTANT REASON 1 */
// 3. pass function into arguments/parameters
def manipulateNumbers(x:Int, y:Int, function:(Int,Int)=>Int): Int = {
  function(x,y)
}
manipulateNumbers(1,2, addition)
manipulateNumbers(1,2, subtract)
manipulateNumbers(1,2, multiply)


/** IMPORTANT REASON 2 */
// 4. return functions from functions
def createAdditionFunction(x:Int): Int => Int = {
  (y:Int) => x + y
}

createAdditionFunction(1)
createAdditionFunction(1)(2)
val addOne: Int => Int = createAdditionFunction(1)
addOne(2)


/** --- Why Use HOF ? --- */
// - 1. makes code more modular and reusable


def firstHOF(x :Int, function: Int =>Int): Int = {
  function(x)
}

def addTwo(n:Int) : Int = n + 2

firstHOF(3, addTwo)



def processTheList(list1:List[String], function:String => String):List[String] = {
  list1.map(function)
}

def makeUpper(word:String):String = {
  word.toUpperCase()
}
def addS(word:String):String = word + "s"

val newList:List[String] = List("Hello", "Tom", "Thomas")
processTheList(newList, makeUpper)
processTheList(List("Hello", "Tom", "Thomas"), addS)



/** --- FOLD LEFT / FOLD RIGHT --- */
// Used to reduce a collection into a single value
val numbers = Seq(1,2,3,4,5,6)
val doubleNumbers = numbers.map(_*2)

// Fold Left Example
// .foldLeft(startingIndex)(acc <operation> current value)
def foldLeftExample = numbers.foldLeft(0)(_+_)

val concatString = newList.foldLeft("")(_+" "+_).trim


case class Male(male:Boolean)
case class Animal(name:String, male: Male)

val animals = List(
  Animal("Thomas the Developer", Male(true)),
  Animal("Lucky the Lunatic",Male(true)),
  Animal("Sally the Seahorse", Male(false)))

val foldAnimalsLeft = animals.foldLeft(List[String]()){ (acc, animal) =>
  val sirOrMadam = animal.male match {
    case Male(true) => "Sir"
    case Male(false) => "Madam"
  }
  acc :+ s"$sirOrMadam ${animal.name}"
}

val foldAnimalsRight = animals.foldRight(List[String]()){ (animal, acc) =>
  val sirOrMadam = animal.male match {
    case Male(true) => "Sir"
    case Male(false) => "Madam"
  }
  acc :+ s"$sirOrMadam ${animal.name}"
}


/** --- TASK 1 --- */

val product = numbers.foldLeft(1)(_*_)

val trueInts = numbers.filter(_.isValidInt)
val evenInts = numbers.filter(_%2==0)

val numOfChars = newList.foldLeft(0){(acc, word)=>
  acc + word.trim.length
}
val numOfChars2 = newList.foldLeft(0)(_+_.trim.length)

val capAndConcat= newList.map(_.capitalize).foldLeft("")(_+" "+_)


/** Returning Functions */

// These all HOF as returning function
def smallRise(salary: Double): (Int, Double) => Double = {
  (lengthOfEmployment: Int, salary:Double) => salary * 1.1
}

def mediumRise(salary: Double): (Int, Double) => Double = {
  (lengthOfEmployment: Int, salary:Double) => salary * 1.2
}
def largeRise(salary: Double): (Int, Double) => Double = {
  (lengthOfEmployment: Int, salary:Double) => salary * 1.5
}

case class Employee(
                     name:String,
                     salary:Double,
                     lengthOfEmployment:Int)

def getPayRise(employee: Employee):(Int, Double) => Double = {
  employee.lengthOfEmployment match {
    case year if year <= 3 => smallRise(employee.salary)
    case year if year <= 5 => mediumRise(employee.salary)
    case year if year > 5 => largeRise(employee.salary)
  }
}

val Fred = Employee(name = "Fred", salary = 30_000.0, lengthOfEmployment = 2)

val choosePayRise = getPayRise(Fred)
val getRaisedSalary = choosePayRise(Fred.lengthOfEmployment, Fred.salary)



/** --- TASK 2 --- */

case class Item(name:String, price:Double)

def smallDiscount(price:Double):Double => Double = {
  price => price * 0.8
}
def mediumDiscount(price:Double):Double => Double = {
  price => price * 0.4
}
def largeDiscount(price:Double):Double => Double = {
  price => price * 0.5
}
def getDiscount(item:Item):Double => Double = {
  item.price match {
    case price if price < 20 => smallDiscount(price)
    case price if price <= 50 => mediumDiscount(price)
    case price if price > 50 => largeDiscount(price)
  }
}

val itemsToDiscount = List(
  Item("Item 1", 5.0),
  Item("Item 2", 10.0),
  Item("Item 3", 20.0),
  Item("Item 4", 30.0),
  Item("Item 5", 15.0),
  Item("Item 6", 40.0),
  Item("Item 7", 50.0),
  Item("Item 8", 60.0),
  Item("Item 9", 100.0)
)
val singleItem = Item("Single Item",5.0)
val itemDiscount = getDiscount(singleItem)
val discountedItem = itemDiscount(singleItem.price)

val discountedItems = itemsToDiscount.map(item => Item(item.name, getDiscount(item)(item.price)))


