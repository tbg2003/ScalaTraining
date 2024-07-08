package Week2.Monday
import scala.io.StdIn.readLine


object HelloName extends App {
  println("Please enter your name:")
  val name = readLine()
  println(s"Hello $name!")
}
