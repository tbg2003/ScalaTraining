
import scala.io.StdIn.readLine

// ###################################
// #       MVP & Extension           #
// ###################################



// Pure fibonacci function
val start:Seq[Int] = Seq(3,4)


def fibonacciPure(n:Int, seq:Seq[Int]): Seq[Int] = {
  val n1:Int = seq.reverse.head
  val n2:Int = seq.reverse.tail.head

  if (n <= 0) seq else fibonacciPure(n-1, seq:+n1+n2)
}
fibonacciPure(4, start)


// Impure fibonacci function
var sequence:Seq[Int] = Seq(3,4)
def fibonacciImpure(n:Int):Seq[Int] = {
  val n1:Int = sequence.reverse.head
  val n2:Int = sequence.reverse.tail.head
  if (n <= 0) sequence else{
    sequence = sequence:+n1+n2
    fibonacciImpure(n-1)
  }
}

fibonacciImpure(5)

// Predefined values

val start:Seq[Int] = Seq(3,4)
def fibonacci3(n:Int=5, seq:Seq[Int]=start): Seq[Int] = {
  val n1:Int = seq.reverse.head
  val n2:Int = seq.reverse.tail.head
  if (n <= 0) seq else fibonacci3(n-1, seq:+n1+n2)
}
val output: Seq[Int] = fibonacci3()

val stringMode:String = ""
def mk2String(n:Int, sequence:Seq[Int], outString:String): String = {
  if (n < sequence.size) mk2String(n+1, sequence, s"$outString ${sequence(n)}") else outString.trim()
}
mk2String(0,output, stringMode)


