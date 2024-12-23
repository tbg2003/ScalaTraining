package Udemy.Implicits

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object MagnetPattern extends App {

  // Use case of type classes, aims at solving problems caused by method overloading

  // method overloading

  class P2PRequest
  class P2PResponse
  class Serializer[T]

  trait Actor {
    def receive(statusCode: Int): Int
    def receive(request: P2PRequest): Int
    def receive(request: P2PResponse): Int
    def receive[T : Serializer](message: T): Int
    def receive[T : Serializer](message: T, statusCode: Int): Int
    def receive(future: Future[P2PRequest]): Int
    //      ...
    // lots of overloads
  }


  // --- PROBLEMS ---
  /*
    1 - type erasure: Future[P2PRequest], Future[P2PRequest] ==>  Future, Future


    2 - lifting doesn't work for all overloads
      val receiveFV = receive _  // ??!

    3 - code duplication

    4 - type inference and default args
   */


  trait MessageMagnet[Result] {
    def apply(): Result
  }

  def receive[R](magnet: MessageMagnet[R]): R = magnet()

  implicit class FromP2PRequest(request: P2PRequest) extends MessageMagnet[Int] {
    override def apply(): Int = {
      println("handling p2p request")
      42
    }
  }

  implicit class FromP2PResponse(request: P2PResponse) extends MessageMagnet[Int] {
    override def apply(): Int = {
      println("handling p2p response")
      24
    }
  }

  receive(new P2PRequest)
  receive(new P2PResponse)

  // ---- Benefits & Drawbacks

    // 1 - no more type erasure problems. Applicable to Futures of different types - looks for implicit conversions before types are erased
  implicit class FromResponseFuture(future: Future[P2PResponse]) extends MessageMagnet[Int] {
    override def apply(): Int = 2
  }

  implicit class FromRequestFuture(future: Future[P2PRequest]) extends MessageMagnet[Int] {
    override def apply(): Int = 3
  }

  println(receive(Future(new P2PRequest)))
  println(receive(Future(new P2PResponse)))

  // 2 - lifting works
  trait MathLib {
    def add1(x : Int): Int = x + 1
    def add1(s : String): Int = s.toInt + 1
  }

    // *"magnetize"*
  trait AddMagnet {
      def apply(): Int
    }

  def add1(magnet: AddMagnet): Int = magnet()

  implicit class AddInt(x: Int) extends AddMagnet {
    override def apply(): Int = x + 1
  }
  implicit class AddString(s: String) extends AddMagnet {
    override def apply(): Int = s.toInt + 1
  }

  // without type parameter for magnet trait
    // --> allows us to create lifted function:
  val addFv = add1 _
  // because apply always returns an Int, not dynamic type
  println(addFv(3))
  println(addFv("3"))

  /*
    Drawbacks:
    1 - verbose
    2 - harder to read
    3 - can't name or place default arguments
    4 - call by name doesn't work correctly
   */

  class Handler {
    def handle(s: => String) = {
      println(s)
      println(s)
    }
  }

  trait HandleMagnet {
    def apply(): Unit
  }

  def handle(magnet: HandleMagnet) = magnet.apply()

  implicit class StringHandle(s: => String) extends HandleMagnet {
    override def apply(): Unit = {
      println(s)
      println(s)
    }
  }

  def sideEffectMethod(): String = {
    println("Hello, Scala")
    "string"
  }

//  handle(sideEffectMethod()) // compiles --- works as expected
  /** Output
   *
   * Hello, Scala
   * string
   * Hello, Scala
   * string
   */


//  handle{
//    println("Hello, Scala")
//    "string"
//  } // compiles --- does NOT work as expected
  /** Output
   *
   * Hello, Scala
   * string
   * string
   */

  /*
    Compiles to:
        handle{
          println("Hello, Scala")
          new StringHandle("string")
        }
   */

}
