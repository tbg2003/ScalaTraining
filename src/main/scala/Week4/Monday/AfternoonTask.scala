package Week4.Monday



import scala.annotation.tailrec

object AfternoonTask extends App{

  object EuroTeams extends Enumeration{
    type Country = Value
    val Germany, Scotland, Hungary, Switzerland = Value
  }

  def printTeams(teams:List[EuroTeams.Value], acc:String = ""):String = {
    teams match {
      case Nil => acc
      case ::(head, next) =>
        val printAesthetic = if(acc.isEmpty)"" else ", "
        printTeams(next, acc+printAesthetic+head.toString)
    }
  }
  println()
  println("Enums to String:")
  println(printTeams(EuroTeams.values.toList))

//   APRIL USED: Value Set, always get the full list?

  sealed class Team(val name: String)

  object Team {
    case object Spain extends Team("Spain")

    case object Croatia extends Team("Croatia")

    case object Albania extends Team("Albania")

    case object Italy extends Team("Italy")
  }


  @tailrec
  def printTeamObjects(teams:List[Team], acc:String = ""):String = {
    teams match {
      case Nil => acc
      case ::(head, next) =>
        val printNice = if(acc.isEmpty)"" else ", "
        printTeamObjects(next, acc+printNice+head.name)
    }
  }
  println()
  println("Objects to String:")
  println(printTeamObjects(List(Team.Italy, Team.Spain, Team.Albania, Team.Croatia)))

  val listToTwenty = (1 to 20).toList

  def isEven(numbers:List[Int], index:Int):Either[String, Boolean] = {
    @tailrec
    def isEvenHelper(numbers:List[Int], index:Int, acc:Int=0):Either[String, Boolean]= {
      numbers match {
        case Nil => Left("Error")
        case ::(head, _) if index == acc => Right(head % 2 == 0)
        case ::(_, tail) => isEvenHelper(tail, index, acc+1)
      }
    }
    isEvenHelper(numbers, index)
  }

  println(isEven(listToTwenty, 3))

  @tailrec
  def sumNumbers(n:Int, acc:Int = 0):Int = {
    if (n<=0) acc
    else sumNumbers(n-1, acc+n)
  }


  @tailrec
  def fibonacciCalc(n:Int, acc:Seq[Int]=Seq(1,1)):Either[String, Seq[Int]] = {
    n match {
      case x if x < 0 => Left(s"Error: input must be positive, $x < 0")
      case x if x == 0 => Right(Seq())
      case x if x == 1 => Right(Seq(1))
      case x if x == 2 => Right(acc)
      case x if x > 2 =>
        val lastX = acc.last
        val secondLastX = acc(acc.length-2)
        fibonacciCalc(x-1, acc++Seq(lastX+secondLastX))
      case _ => Left(s"Something went wrong")
    }
  }
}
