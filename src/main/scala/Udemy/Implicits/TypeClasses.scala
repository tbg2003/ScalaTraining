package Udemy.Implicits

object TypeClasses extends App {

  trait HTMLWritable {
    def toHTML: String
  }

  case class User(name: String, age:Int, email: String) extends HTMLWritable {
    override def toHTML: String = s"<div>$name ($age yo) <a href=$email/> </div>"
  }

  val tom = User("Tom", 21, "tbg2003@icloud.com")

  // METHOD 1
  object HTMLSerializerPM {
    def serializeToHtml(value: Any) = value match {
      case User(n, a, e) =>
      case _ =>
    }
  }

  // METHOD 2

  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

  object UserSerializer extends HTMLSerializer[User] {
    def serialize(user: User): String = s"<div>${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  println(UserSerializer.serialize(tom))

  import java.util.Date
  object DateSerializer extends HTMLSerializer[Date] {
    override def serialize(date: Date): String = s"<div>${date.toString}</div>"
  }

  object PartialUserSerializer extends HTMLSerializer[User] {
    override def serialize(user: User): String = s"<div>${user.name}</div>"
  }


  // TYPE CLASS
  trait MyTypeClassTemplate[T] {
    def action(value: T): String
  }

  object MyTypeClassTemplate {
    // this apply method 'surfaces out' the implicit instance, can now use the entire interface
    def apply[T](implicit instance: MyTypeClassTemplate[T]) = instance
  }

  /**
   * Task 1 :  Equality
   */

//  trait Equal[T] {
//    def apply(a: T, b:T): Boolean
//  }
//
//  object NameEquality extends Equal[User] {
//    override def apply(a: User, b: User): Boolean = a.name == b.name
//  }
//
//  object FullEquality extends Equal[User] {
//    override def apply(a: User, b: User): Boolean = a.name == b.name && a.email == b.email
//  }

  object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]):String =
      serializer.serialize(value)

    def apply[T](implicit serializer: HTMLSerializer[T]) = serializer
  }

  implicit object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div style: color=blue>$value</div>"
  }

  implicit object ImpUserSerializer extends HTMLSerializer[User] {
    def serialize(user: User): String = s"<div>${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  //  println(HTMLSerializer.serialize(42)(IntSerializer)) -- when IntSerializer not defined as implicit
  println(HTMLSerializer.serialize(42))
  println(HTMLSerializer.serialize(tom))

  // have access to the entire type class interface - result of defining the apply method
  println(HTMLSerializer[User].serialize(tom))


  /**
   * Task 2 : IMPLEMENT TYPE CLASS PATTERN FOR EQUALITY TYPE CLASS
   */

//  trait Equal[T] {
//        def apply(a: T, b:T): Boolean
//      }
//
//  object Equal {
//    def apply[T](a: T, b: T)(implicit equalizer: Equal[T]): Boolean =
//      equalizer.apply(a, b)
//  }
//
//  implicit object ImpNameEquality extends Equal[User] {
//    override def apply(a: User, b: User): Boolean = a.name == b.name
//  }
//
  val anotherTom = User("Tom", 30, "tburtgray@gmail.com")
//
//  println(Equal.apply(tom, anotherTom))
//  // AD-HOC polymorphism



  implicit class HTMLEnrichment[T](value: T) {
    def toHTML(implicit serializer: HTMLSerializer[T]): String = serializer.serialize(value)
  }

  println(tom.toHTML) // println(new HTMLEnrichment[User](tom).toHTML(ImpUserSerializer))

  /**
   * Allows us to extend to new types
   */

  println(2.toHTML)
//  println(tom.toHTML(PartialUserSerializer))
  /**
   * type class : HTMLSerializer
   * type class instances (some of which are implicit): UserSerializer, IntSerializer
   * conversion with implicit classes: HTMLEnrichment
   */


  /**
   * Task 3 : Improve Equal type class with implicit conversion class
   */

  trait Equal[T] {
    def apply(a: T, b: T): Boolean
  }

  object Equal {
    def apply[T](a: T, b: T)(implicit equalizer: Equal[T]): Boolean =
      equalizer.apply(a, b)
  }

  implicit object NameEquality extends Equal[User] {
    override def apply(a: User, b: User): Boolean = a.name == b.name
  }

  implicit class TypeSafeEqual[T](value: T){
    def ===(other: T)(implicit equalizer: Equal[T]): Boolean = equalizer.apply(value, other)
    def !==(other: T)(implicit equalizer: Equal[T]): Boolean = ! equalizer.apply(value, other)
  }

  val notTom = User("Tim", 21, "tim@email.com")
  println(tom === anotherTom)
  println(tom !== notTom)


  def htmlBoilerPlate[T](content: T)(implicit serializer: HTMLSerializer[T]): String =
    s"<html><body>${content.toHTML(serializer)}</body></html>"

// : HTMLSerializer --> context bound, inject here implicit parameter of type HTMLSerializer of type T
  // injected so cannot use serializer by name, unless using `implicitly'
  def htmlSugar[T : HTMLSerializer](content: T): String = {
    val serializer = implicitly[HTMLSerializer[T]]
    s"<html><body>${content.toHTML(serializer)}</body></html>"
  }

  // implicitly

  case class Permissions(mask: String)
  implicit val defaultPermissions: Permissions = Permissions("0744")

  // in some other part of the code
  // implicitly surfaces out the implicit val
  val standardPerms = implicitly[Permissions]

}
