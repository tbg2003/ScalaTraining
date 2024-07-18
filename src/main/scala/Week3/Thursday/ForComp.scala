package Week3.Thursday

object ForComp extends App{

  // <-
  // Being lazy, printing instead of testing for notes

  /** --- Classic --- */
  def retrieveNumbers: Seq[Int] = for (numbers <- 1 to 10) yield numbers

  /** --- Apply Operators --- */
  def squareNumbers: Seq[Int] = for (numbers <- 1 to 10) yield numbers * numbers
  println(squareNumbers)

  /** --- Apply If Guards --- */
  def ifGuardNumbers: Seq[Int] = for (numbers <- 1 to 10 if numbers % 2 == 0) yield numbers
  println(ifGuardNumbers)


  /** --- Use with case classes --- */
  sealed trait Colour
  case object Red extends Colour
  case object Blue extends Colour
  case object Green extends Colour


  case class Pencil(colour:Colour, sharpened: Boolean)

  def getPencils:List[Pencil] = for {
    colour <- List(Red, Blue, Green)
    sharpened <- List(true, false)
  } yield Pencil(colour, sharpened)

  println(getPencils)
}
