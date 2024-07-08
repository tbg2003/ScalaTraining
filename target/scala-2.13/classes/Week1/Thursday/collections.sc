// Introduction into basic collections in Scala

// ######################
// Seq & List

val seq1: Seq[Int] = Seq(1, 2, 3)
val list1: List[Int] = List(1, 2, 3)
// Difference:
//A list is a linear sequence i.e. a list is the most common type of sequence

// ######################
// Map
// Key Value pairs : key -> value
val map1: Map[String, Int] = Map(
  "one" -> 1,
  "two" -> 2,
  "three" -> 3,
)

// ######################
// Accessing data from these types

// Seq
val getSeqData: Int = seq1(1)
val getSeqHead: Int = seq1.head // same as seq(0)
val getSeqFirst: Seq[Int] = seq1.tail // all but seq(0)

// List
val getListData: Int = list1(1)
val getListHead: Int = list1.head // same as seq(0)
val getListFirst: Seq[Int] = list1.tail // all but seq(0)

// Map
val getMapData:Int = map1("one")

// ######################
// Manipulating data in Seq/List

val doubleSeq: Seq[Int] = seq1.map{
  number => number * 2
}

val tripleList: List[Int] = list1.map{
  _ * 3
}

// ######################
// Manipulating Maps
val tripleMap: Map[String, Int] = map1.map{
  case (key, value) => (key, value * 3)
}

val changeMapKey: Map[String, Int] = map1.map{
  case (key, x) => (key.toUpperCase(), x)
}

val changeBoth: Map[String, Int] = map1.map{
  case (key, value) => (key.toLowerCase(), value/3)
}

// ######################
// Filtering

// Seq & List
val filteredSeq: Seq[Int] = seq1.filter{
  number => number > 1
}
val filteredList: List[Int] = list1.filter{
  _ > 1 // Placeholder, same as x => x > 1
}
val filterNotList: List[Int] = list1.filterNot(
  _ >= 2
)

// Map - uses tuple as input [x,y]
val filteredMap: Map[String, Int] = {
  map1.filter(number => number._2 > 1)
}

// ######################
// Exists & Contains

//Exists
val existsInList: Boolean = list1.exists{
  _ % 2 == 0
}

val existsInSeq: Boolean = seq1.exists{
  _ % 2 == 0
}

val existsInMap: Boolean = map1.exists{
  _._2 %2 == 0
}

//Contains
val seqContains: Boolean = seq1.contains(1)
val listContains: Boolean = list1.contains(2)
val mapContains: Boolean = map1.contains("two") // Can only check for key