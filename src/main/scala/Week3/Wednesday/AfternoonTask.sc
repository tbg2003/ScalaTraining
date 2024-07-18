import java.util.Date
import java.time.{LocalDate, Period}
import java.time.format.DateTimeFormatter

/** --- Afternoon Task --- */

// ##############################################################
// #                         MVP                                #
// ##############################################################

// Question 1
val countryName:String = "England"

countryName.strip().toLowerCase() match{
  case "england" => "London"
  case "France" => "Paris"
  case _ => "Sorry Im not familiar with that country"
}

// Question 2
sealed trait StarSign
case object Capricorn extends StarSign
case object Aquarius extends StarSign
case object Pisces extends StarSign
case object Aries extends StarSign
case object Taurus extends StarSign
case object Gemini extends StarSign
case object Cancer extends StarSign
case object Leo extends StarSign
case object Virgo extends StarSign
case object Libra extends StarSign
case object Scorpio extends StarSign
case object Sagittarius extends StarSign

case class Person(
                   fullName: String,
                   dateOfBirth: LocalDate,
                   lastCountryLivedIn: String,
                   occupation: Option[String],
                   dateOfDeath: Option[LocalDate],
                   age: Int,
                   ageAtDeath: Option[Int],
                   starSign: StarSign
                 )


object PersonFactory{

  //    Method for Question 3
  def horoscope(birthDate:LocalDate):(StarSign, String) = {
  val starSign:StarSign = calculateStarSign(birthDate)

    val message = starSign match {
      case Aquarius => "You are innovative and forward-thinking."
      case Pisces => "You are compassionate and artistic."
      case Aries => "You are bold and ambitious."
      case Taurus => "You are reliable and patient."
      case Gemini => "You are adaptable and outgoing."
      case Cancer => "You are intuitive and nurturing."
      case Leo => "You are confident and charismatic."
      case Virgo => "You are analytical and kind."
      case Libra => "You are fair-minded and social."
      case Scorpio => "You are passionate and resourceful."
      case Sagittarius => "You are adventurous and optimistic."
      case Capricorn => "You are disciplined and responsible."
    }
    (starSign, message)
  }

  def calculateAge(dateOfBirth:LocalDate, dateOfDeath:Option[LocalDate]):Int = {
    val dateEnd: LocalDate = dateOfDeath.getOrElse(LocalDate.now())
    val age:Int = Period.between(dateOfBirth, dateEnd).getYears
    age
  }

  def calculateStarSign(dateOfBirth:LocalDate):StarSign = {
    val month:Int = dateOfBirth.getMonthValue
    val day:Int = dateOfBirth.getDayOfMonth
    (month, day) match {
      case(12, x) if x > 21 => Capricorn
      case (1, x) if x <= 20 => Capricorn

      case(1, x) if x > 20 => Aquarius
      case(2, x) if x <= 19 => Aquarius

      case(2, x) if x > 20 => Pisces
      case(3, x) if x <= 20 => Pisces

      case(3, x) if x > 21 => Aries
      case(4, x) if x <= 20 => Aries

      case(4, x) if x > 21 => Taurus
      case(5, x) if x <= 20 => Taurus

      case(5, x) if x > 21 => Gemini
      case(6, x) if x <= 21 => Gemini

      case(6, x) if x > 21 => Cancer
      case(7, x) if x <= 22 => Cancer

      case(7, x) if x > 22 => Leo
      case(8, x) if x <= 23 => Leo

      case(8, x) if x > 23 => Virgo
      case(9, x) if x <= 23 => Virgo

      case(9, x) if x > 23 => Libra
      case(10, x) if x <= 23 => Libra

      case(10, x) if x > 23 => Scorpio
      case(11, x) if x <= 22 => Scorpio

      case(11, x) if x > 22 => Sagittarius
      case(12, x) if x <= 21 => Sagittarius
    }
  }
  
  def createPerson(fullName: String,
                   dateOfBirth: LocalDate,
                   lastCountryLivedIn: String,
                   occupation: Option[String],
                   dateOfDeath: Option[LocalDate]):Person = {

    val age:Int = calculateAge(dateOfBirth, dateOfDeath)

    val ageAtDeath:Option[Int] = dateOfDeath match {
      case Some(x) => Some(age)
      case None => None
      case _ => None
    }

    val starSign:StarSign = calculateStarSign(dateOfBirth)

    val newPerson:Person = Person(
      fullName = fullName,
      dateOfBirth = dateOfBirth,
      lastCountryLivedIn = lastCountryLivedIn,
      occupation = occupation,
      dateOfDeath = dateOfDeath,
      age = age,
      ageAtDeath = ageAtDeath,
      starSign = starSign)
    newPerson
  }
}


val tom:Person = PersonFactory.createPerson(
  fullName = "Tom Burt-Gray",
  dateOfBirth = LocalDate.of(2003,4,7),
  lastCountryLivedIn = "England",
  occupation = Some("Full Stack Developer"),
  dateOfDeath = None)

tom.age
PersonFactory.horoscope(tom.dateOfBirth)


// Question 4

sealed trait AnimalType
object Mammal extends AnimalType
object Bird extends AnimalType
object Fish extends AnimalType
object Reptile extends AnimalType
object Amphibian extends AnimalType

abstract class Animal{
  val animalType:AnimalType
  val speciesName:String
}

abstract class Mammal extends Animal{
  override val animalType: AnimalType = Mammal
}

abstract class Reptile extends Animal{
  override val animalType: AnimalType = Reptile
}

case class Rhino(name:String, age:Int, speciesName:String) extends Mammal
case class Elephant(name:String, age:Int, speciesName:String) extends Mammal
case class Monkey(name:String, age:Int, speciesName:String) extends Mammal
case class Turtle(name:String, age:Int, speciesName:String) extends Reptile
case class Tiger(name:String, age:Int, speciesName:String) extends Mammal



// ##############################################################
// #                         Extension                          #
// ##############################################################


// Question 1

val criticallyEndangered:List[String] = List("Black Rhino", "African forest Elephant", "Orangutan", "Hawksbill Turtle", "Sunda Tiger", "Javan Rhino")
val cleanedEndangeredSpeciesList = criticallyEndangered.map(_.strip().toLowerCase())

def isEndangered(species:String):Boolean= {
  if (cleanedEndangeredSpeciesList.contains(species.strip().toLowerCase())) true else false
}

def concernMessage(speciesName:String):String = {
  s"The $speciesName is endangered!!, GET UP AND HELP"
}
def noConcernMessage(speciesName:String):String = {
  s"The $speciesName is not endangered, you can chill for now"
}

def getMammalFact(mammal: Mammal):String = {

  mammal match {
    case Elephant(_, _, speciesName) if isEndangered(speciesName) => concernMessage(speciesName)
    case Elephant(_, _, speciesName) => noConcernMessage(speciesName)

    case Monkey(_, _, speciesName) if isEndangered(speciesName) => concernMessage(speciesName)
    case Monkey(_, _, speciesName) => noConcernMessage(speciesName)

    case Rhino(_, _, speciesName) if isEndangered(speciesName) => concernMessage(speciesName)
    case Rhino(_, _, speciesName) => noConcernMessage(speciesName)

    case Tiger(_, _, speciesName) if isEndangered(speciesName) =>concernMessage(speciesName)
    case Tiger(_, _, speciesName) => noConcernMessage(speciesName)

    case _ => s"I don't know anything about $mammal"
  }
}

def getReptileFact(reptile: Reptile):String = {
  reptile match {
    case Turtle(_, _, speciesName) if isEndangered(speciesName) =>concernMessage(speciesName)
    case Turtle(_, _, speciesName) => noConcernMessage(speciesName)
    case _ => s"No reptile fact about $reptile"
  }
}

def getAnimalFact(animal:Animal):String = {
  animal match {
    case mammal: Mammal => getMammalFact(mammal)
    case reptile: Reptile => getReptileFact(reptile)
    case _ => s"No fact about $animal today"
  }
}

val blackRhino:Rhino = Rhino(name = "rhino", age = 4, speciesName = "black rhino")
val otherRhino:Rhino = Rhino(name = "rhino2", age = 4, speciesName = "White rhino")
val orangutan:Monkey = Monkey(name = "monkey", age = 4, speciesName = "Orangutan")
val turtle:Turtle = Turtle(name = "turtle", age = 4, speciesName = "Hawksbill turtle")
val tiger:Tiger = Tiger(name = "tiger", age = 4, speciesName = "Sunda Tiger")

getAnimalFact(blackRhino)
getAnimalFact(otherRhino)
getAnimalFact(orangutan)
getAnimalFact(turtle)
getAnimalFact(tiger)

// ##############################################################
// #                          Research                          #
// ##############################################################
/** --- WHy did code snippet not work? ---
 * type lost during compilation,
 *
 *
 * */