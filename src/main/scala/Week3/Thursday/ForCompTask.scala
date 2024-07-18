package Week3.Thursday

object ForCompTask extends App {

  /** --- Task 1 --- */

  def makeAllCaps(names:List[String]):List[String] = for {
    nameInCaps <- names.map(_.toUpperCase())
  } yield nameInCaps

  /** --- Task 2 --- */

  def makeFirstCap(names:List[String]):List[String] = for {
    nameInCaps <- names.map(_.toLowerCase().capitalize)
  } yield nameInCaps

  /** --- Lazy Testing Tasks 1 & 2 --- */

  println(makeAllCaps(List("tom", "jamie", "vicky")))
  println(makeFirstCap(List("tom", "jamie", "vicky")))

}
