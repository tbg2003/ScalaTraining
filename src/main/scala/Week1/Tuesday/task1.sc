// ################
// MVP

// 1
// false because maths
val a: Boolean = 3 + 4 * 57 < 300
// false because 144 / 12 == 12
val b: Boolean = 144 / 12 > 12 | 144 / 12 == 5
// true as c becomes before d in alphabet
val c: Boolean = "Cat" < "Dog"
// false because r comes after h
val d: Boolean = "Rabbit" < "Hamster"
// true because remainder 1 means not divisible by 2
val e: Boolean = 17 % 2 == 1
// true because all expressions are true
val f: Boolean = 75 / 9 < 30 && 89 / 6 < 20

// 2
// the string "I love scala already" is essentially a value, whereas println is a function which takes a string value as an input and outputs it

// 3
// I find the type for longs a little strange as they do not follow the general pattern, need the L at end
val long1: Long = 999999999999999999L
// Everything else is similar to what I have learnt previously

// ################
// Extension

// 1 : False because the binary/ hex value of S is different to s
val q1: Boolean = "STRING" == "string"

// 2 : No as they are of different types
val x1: Int = 1
val x2: String = if (x1==1) "one" else "other"

// 3 : same as previous answer
val y1: String = "one"
val y2: Int = if (y1=="one") 1 else 0

// 4 : You could cast it as a different type
val z1: Int = 1
val z2: String = s"$z1"

// 5 :
val myString: String = "hello world!"
val myUpperCaseString: String = myString.toUpperCase()
println(s"Lower case string: $myString \nUpper case string: $myUpperCaseString")

val firstLetter: String = myString.capitalize
// ################
// Research
//val new: String = "Error"
// you cannot make val called 'new' as new is a keyword used to define a class, therefore the compiler gets confused as it expects an identifier but get another keyword instead
// you must edit the word- give it a capital letter
// You cannot use any other word which relates to a predefined or built in function
