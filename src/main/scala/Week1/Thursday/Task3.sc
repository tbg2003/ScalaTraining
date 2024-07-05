// ##################
// # MVP
// ##################

//1
val numList: List[Int] = (1 to 9).toList

val colourSeq: Seq[String] = Seq("Red", "Yellow", "Blue")

val multiplyNums: List[Int] = numList.map(_*3)

val filteredColours: Seq[String] = colourSeq.filterNot(_=="Blue")

val containOrgange: Boolean = colourSeq.contains("Orange")

val isOdd: Seq[Boolean] = numList.flatMap(n=>
  if (n%2 == 1) Seq(true)
  else Seq(false)
)
val isOdd2: Seq[Boolean] = numList.map{
  _%2 != 0
}

// Method works however what is dog dies? or get a new dog?
// Better to just create a long map for all pets - with the name as a key as they need to be unique
val dogNames: List[String] = List("Charlie", "Buster")
val dodoNames: List[String] = List("Henry")
val goblinSharkNames: List[String] = List("Penelope")

val pets: Map[String, List[String]] = Map(
  "dog" -> dogNames,
  "dodo" -> dodoNames,
  "goblin shark" -> goblinSharkNames
)


// Overly complex
val petNames: List[String] = pets.filter{_._1 == "dog"}.flatMap(_._2).toList
// Simple
val dogNames: List[String] = pets{"dog"}

// ##################
// # Extension
// ##################

//1
val seq1: Seq[Int] = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)

val filtered1: Seq[Int] = seq1.filterNot(n=>
  n%3 == 0 || n%4==0
)

val filtered2: Seq[Int]= seq1.flatMap(n=>
  if (!(n%3==0 || n%4==0)) Seq(n) else Seq()
)

val third: Int = seq1.tail.tail.head

// ##################
// # Research
// ##################


/* Q1
Should give true as the Set should discretely discard all duplicates therefore:
Set(2,1,1) -> Set(2,1)
Due to sets not having an order
Set(1,2) == Set(2,1) so true
 */
Set(1,2) == Set(2,1,1)
/* Q2
List (3,4) == Seq(3,4) should be true
As a list is a type of sequence it is essentially saying a type of sequence which contains
3,4 == a sequence which contains 3, 4. Same order of number so virtually identical when viewed
linearly
So true

List(5,6) == Set(5,6) should be false
A List is a type of Seq which is a different type of collection than a Set
The datatype of a list can contain duplicates, a set cannot, they possess different
properties and traits, not the same
 */

List(3,4) == Seq(3,4)
List(5,6) == Set(5,6)