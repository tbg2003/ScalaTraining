package Week3.Friday

// Import for futures - global is a lazy val, not executed until we call them

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, FiniteDuration}
import Week3.Thursday.Eithers._

import scala.concurrent.impl.Promise
import scala.util.{Failure, Success}
/** --- Futures --- */
// Place holder for a future value that doesn't currently exist
// Enables parallel execution, tasks performed async and quicker
// Resolve around Execution Contexts
// Use scala.concurrent


object Futures extends App{

  def addition(x:Int, y:Int):Int = {
    Thread.sleep(1000) // long computation time
    x + y
  }
//  Future - takes in an expression and an execution context, hence the import of ExecutionContext...
  def additionInTheFuture:Future[Int] = Future{
    addition(1, 2)
  }
//  builtin FiniteDuration
  val waitTime:FiniteDuration = Duration(5, TimeUnit.SECONDS)
//  Printing a Future - Await blocks main thread, await only if essential, not usually seen in development code
  println(Await.result(additionInTheFuture, waitTime))
//  If future waits to long, sleep() Time > Duration Time --> TimeOut Error


  /** --- Morning Task --- */

  def fetchIsOddOrError(x:Int):Future[Either[NewError, String]] = Future {
    Thread.sleep(1000)
    isOdd(x)
  }

  def eventualIsOddOrError(): Future[Either[NewError, String]] =
    fetchIsOddOrError(4)

  def matchIsOddError = eventualIsOddOrError().value match {
    case Some(Success(complete)) => s"Future completed: $complete"
    case None => "Future did not complete in the given time"
  }

  println(Await.ready(eventualIsOddOrError(), waitTime))
}
