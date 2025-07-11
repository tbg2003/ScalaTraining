package DesignPatterns

import scala.annotation.tailrec

object TrampolinePattern extends App {

  sealed trait Computation[A]

  class Continue[A](n: => Computation[A]) extends Computation[A] {
    lazy val next: Computation[A] = n
  }
  case class Done[A](result: A) extends Computation[A]


  def even(i: Int): Computation[Boolean] = i match {
    case 0 => Done(true)
    case _ => new Continue(odd(i - 1))
  }

  def odd(i: Int): Computation[Boolean] = i match {
    case 0 => Done(false)
    case _ => new Continue(even(i - 1))
  }

  @tailrec
  def run[A](computation: Computation[A]): A = computation match {
    case Done(a) => a
    case c: Continue[A] => run(c.next)
  }

  val result = run(odd(5001))
  println(result)

}

