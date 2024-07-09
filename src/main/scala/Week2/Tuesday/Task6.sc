// ##############################################################
// #                            MVP                             #
// ##############################################################

/** --- Question 1 --- */
// Main difference between a class and case class is that case classes are used to model immutable data and come equipped
// with predefined methods

/** --- Question 2 --- */
// a) case class Person(name:String, age:Int, nationality:String, isStudent:Boolean)
// Will run

// b) val firstPerson = new Person("Carlos", 23, "Spanish", true)
// Will run but new keyword not necessary

// c) val secondPerson = Person("Carlos", 23, "Spanish", true)
// Will run

// d) val thirdPerson = Person("Chloe", 25, "British", false)
// Will run

// e) class Animal(name:String, colour:String, pet:Boolean)
// Will run

// f) val cat = Animal("cat", "black", true)
// Wont run, normal class needs keyword 'new'

// g) val dog = new Animal("dog", "white", true)
// Will run

/** --- Question 3 --- */
// after using the == operator the vals which return true are
// chocolateMuffin == anotherChocolateMuffin == oneMoreChocolateMuffin


// ##############################################################
// #                         Extension                          #
// ##############################################################

/** --- Question 1 --- */
case class Student(name:String, yearOfStudy:Int, isBroke:Boolean)

sealed trait Subject
case object ComputerScience extends Subject
case object Economics extends Subject
case object Philosophy extends Subject


/** --- Question 2 --- */
val student1:Student = Student(name = "Tom", yearOfStudy = 3, isBroke = true)
val student2:Student = Student(name = "Alex", yearOfStudy = 1, isBroke = false)
val student3:Student = Student(name = "Jon", yearOfStudy = 2, isBroke = true)


/** --- Question 3 --- */
val student1Friend:Student = student1.copy(name = "George")
val student2Friend:Student = student2.copy(name = "Henry", isBroke = true)
val student3Friend:Student = student3.copy(name = "Ron")


/** --- Question 4 --- */
println(student1Friend.toString)
println(student2Friend.toString)
println(student3Friend.toString)


/** --- Question 5 --- */
student1.equals(student2)
student2.equals(student3)
student2.equals(student2.copy())
student1.equals(student2.copy(name = "Tom", yearOfStudy = 3, isBroke = true))

// ##############################################################
// #                         Research                           #
// ##############################################################

/** --- Research Task 1 --- */
val student1Copy:Student = student1.copy()

student1.getClass
student1Copy.getClass

student1.equals(student1Copy)
student1.eq(student1Copy) // doesn't point to same place in memory so .eq is false

student1.hashCode()
student2.hashCode()
student1.hashCode().equals(student2.copy(name = "Tom", yearOfStudy = 3, isBroke = true).hashCode())

student1.canEqual(student2)

student1.productArity

student1.productElement(0)

student1.productIterator.toList


/** --- Research Task 2 --- */
// == and .equals methods
// Only difference I found is the error which occurs from null

// ==
student1 == null
null == student1

// .equals
student1.equals(null)
null.equals(student1) // Cannot handle this well -> NullPointerException