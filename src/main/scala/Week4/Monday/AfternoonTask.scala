package Week4.Monday

import scala.annotation.tailrec

object AfternoonTask extends App{

  def fibonacciCalc(x:Int):Seq[Int] = {
    @tailrec
    def fibonacciHelper(x:Int, acc:Seq[Int]=Seq(1,1)):Seq[Int] = {
      if (x<=2)acc
      else{
        val lastX = acc.last
        val secondLastX = acc(acc.length-2)
        fibonacciHelper(x-1, acc++Seq(lastX+secondLastX))
      }
    }
    fibonacciHelper(x)
  }

}
