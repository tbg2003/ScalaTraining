// Thursday Afternoon Task

// ##########################
// Task 1

//1
val names: Seq[String] = Seq("Tom", "Spencer", "Jamie", "Tayamul", "Arei", "Anthony", "April", "Bilal", "Joe", "Muhammed", "Roshan")
val colours: Map[Int, String] = Map(
  1 -> "red",
  2 -> "yellow",
  3 -> "blue",
  4 -> "green"
)

//2
val colourPlusOne: Map[Int, String] = colours.map{
  case (key,value) => (key + 1, value)
}

//3
val oddColour: Map[Int, String] = colours.filterNot{
  _._1 % 2 == 0
}

//4
val containsR: Boolean = names.exists{
  _.toLowerCase.contains("r")
}

// ##########################
// Task 2

//1
// Sets contain unique items, cannot have duplicates within same set i.e. never satisfies Xn == Xm
// Var creates mutable sets & Val defines immutable
// Immutable by default if not defined
// Can be used to remove duplicates
val setNames = names.toSet
//2
// flatMap() is the same as (x.map()).flatten()
// This essentially maps over the items and then flattens it, could be used when individual items of a list need to be manipulated independently dependent on their value

val findScalaGod: Seq[String] = names.flatMap( s =>
  if(s.toLowerCase() == "april") Seq(s.toUpperCase() + " The Scala God")
  else Seq(s)
)

// Good for nested data
// i.e. maps within maps