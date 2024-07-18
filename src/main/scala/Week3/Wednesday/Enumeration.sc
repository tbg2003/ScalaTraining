/** --- Enumeration for finite sets of data --- */

/** Native */

object Weekday extends Enumeration{
  val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value
}
// Accessing the values
Weekday.Monday.toString

// Acronyms
object BetterWeekday extends Enumeration{
//   value - "assigned acronym"
  val Monday = Value("Mon")
  val Tuesday = Value("Tue")
  val Wednesday = Value("Wed")
  val Thursday = Value("Thur")
  val Friday = Value("Fri")
  val Saturday = Value("Sat")
  val Sunday = Value("Sun")
}

BetterWeekday.Saturday // -> get the value "Sat"
BetterWeekday.values // -> get all values

// When assigning values for each -> They are all inherently ordered
BetterWeekday.Monday < BetterWeekday.Sunday


object ReOrderWeekday extends Enumeration{
//  value - assigned index position
  val Monday = Value(1, "Mon")
  val Tuesday = Value(2, "Tue")
  val Wednesday = Value(3)
  val Thursday = Value(4)
  val Friday = Value(5)
  val Saturday = Value(6)
  val Sunday = Value(0)
}

ReOrderWeekday.Monday < ReOrderWeekday.Sunday
ReOrderWeekday.Monday
ReOrderWeekday.Wednesday


def exhaustiveMatch(weekday: BetterWeekday.Value): String = {
  weekday match {
    case BetterWeekday.Monday => "Urgh its" + weekday
    case BetterWeekday.Tuesday => "Tuesday"
    case BetterWeekday.Wednesday => "Anything"
    case BetterWeekday.Thursday => "To"
    case BetterWeekday.Friday => "Show"
    case BetterWeekday.Saturday => "Exhaustive"
    case BetterWeekday.Sunday => "Matching"
  }
}

// Doesn't throw and error -> ensure exhaustive matching and wildcard to be safe
def nonExhaustiveMatch(weekday: BetterWeekday.Value): String = {
  weekday match {
    case BetterWeekday.Monday => "Urgh its" + weekday
    case BetterWeekday.Tuesday => "Tuesday"
  }
}



/** --- Sealed case objects --- */
// Sealed trait and case objects
sealed trait Weekday
case object Monday extends Weekday
case object Tuesday extends Weekday
case object Wednesday extends Weekday
case object Thursday extends Weekday
case object Friday extends Weekday
case object Saturday extends Weekday
case object Sunday extends Weekday

def caseObjectMatch(weekday1: Weekday):String = {
  weekday1 match {
    case Monday => "Mon"
    case Tuesday => "Tue"
    case Wednesday => "Wed"
    case Thursday => "Thur"
    case Friday => "Fri"
    case Saturday => "Sat"
    case Sunday => "sun"
  }
}

// Must be exhaustive
// No easy way to access all values
// No default ordering


/** --- Enumeration Task --- */

object EyeColour extends Enumeration{
  val Blue = Value("Blue Eyes")
  val Green = Value("Green Eyes")
  val Brown = Value("Hazel Eyes")
}

def eyeColourMatch(colour:EyeColour.Value):String = {
  colour match {
    case EyeColour.Blue => "Blue"
    case EyeColour.Green => "Green"
    case EyeColour.Brown => "Hazel"
  }
}

sealed trait EyeColour
case object Blue extends EyeColour
case object Green extends EyeColour
case object Brown extends EyeColour

def eyeColourTraitMatch(colour: EyeColour):String = {
  colour match {
    case Blue => "Blue"
    case Green => "Green"
    case Brown => "Hazel"
  }
}

def printValues(colour: EyeColour): EyeColour.Value = {
  colour match {
    case Blue => EyeColour.Blue
    case Green => EyeColour.Green
    case Brown => EyeColour.Brown
  }
}
