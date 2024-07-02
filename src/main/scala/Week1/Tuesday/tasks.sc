// #######################
// Task 1 - Vals and vars
val name: String = "Tom"
// name = "Thomas" // reassignment error
var age: Int = 21
age = 21

println(s"$name is $age years old")

// #######################
// TASK 2 - Shopping list
val lemonCost: Int = 5 * 30
val flourCost: Int = 2 * 90
val ciderCost: Double = 6 * (2.5 * 100)
val totalCost: Double = lemonCost + flourCost + ciderCost

println(s"total cost is $totalCost pence")

// #######################
// Task 3 - Types
val myInteger: Int = 20
val myString: String = "Hello"
val myBool: Boolean = true
val myDouble: Double = 0.1

//val myInteger: Int = 2147483649 // Must convert to long 2147483649L
//val myInteger: Int = 0.3 // Must convert to Double

// #######################
// Task 4 - Operators
val a: Int = 12
val b: Int = 4
val c: Int = 5
val myTrue: Boolean = true
val myFalse: Boolean = false

// Arithmetic
val addition: Int = a + b
val subtraction: Int = a - b
val division: Int = a / b
val multiplication: Int = a * b
val modulus: Int = a % c

// Relational
val equality: Boolean = a == b
val inequality: Boolean = a != b
val lessThan: Boolean = a <= b
val greaterThan: Boolean = a >= b

// Logical
val and: Boolean = myTrue && myTrue
val or: Boolean = myTrue || myFalse
val not: Boolean = !myFalse

// #######################
// Task 5 - Operators in practise

val a: Boolean = 4 + 3 * 55 < 300 // Scala abides by BODMASS
val b: Boolean = 96/12 >= 8
val c: Boolean = "Hamster" < "Hippo"
val d: Boolean = 8%2 == 0
val e: Boolean = 156 / 8 < 24 && 54 < 20 && 54 / 4 < 14

// #######################
// Shortcuts
// cmd + D --> duplicates the line
