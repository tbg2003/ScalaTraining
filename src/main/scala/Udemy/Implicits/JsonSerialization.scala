package Udemy.Implicits

import java.util.Date

object JsonSerialization extends App {

  // ---- MY OBJECTS ----
  case class User(name: String, age: Int, email:String)
  case class Post(content: String, createdAt: Date)
  case class Feed(user: User, posts:List[Post])



  // ---- Custom Json Values for defined types ----
  sealed trait JsonValue { // intermediate data type
    def stringify: String
  }

  final case class JsonString(value: String) extends JsonValue {
    override def stringify: String =
    "\"" + value + "\""
  }

  final case class JsonNumber(value:Int) extends JsonValue {
    override def stringify: String = value.toString
  }

  final case class JsonArray(values: List[JsonValue]) extends JsonValue{
    override def stringify: String = values.map(_.stringify).mkString("[", ",", "]")
  }

  final case class JsonObject(values: Map[String, JsonValue]) extends JsonValue {
    override def stringify: String = values.map{
      case (key, value) => "\"" + key + "\":" + value.stringify
    }.mkString("{", ",", "}")
  }



  // ---- Convert to Json ----
  trait JsonConverter[T] {
    def convert(value: T): JsonValue
  }

  implicit class JsonRich[T](value: T) {
    def toJson(implicit converter: JsonConverter[T]): JsonValue =
      converter.convert(value)
  }

  // existing data types
  implicit object StringConverter extends JsonConverter[String] {
    override def convert(value: String): JsonValue = JsonString(value)
  }

  implicit object NumberConverter extends JsonConverter[Int] {
    override def convert(value: Int): JsonValue = JsonNumber(value)
  }

  // custom data types
  implicit object UserConverter extends JsonConverter[User] {

    override def convert(user: User): JsonValue = JsonObject(Map(
      "name" -> JsonString(user.name),
      "age" -> JsonNumber(user.age),
      "email" -> JsonString(user.email)
    ))
  }

  implicit object PostConverter extends JsonConverter[Post] {

    override def convert(post: Post): JsonValue = JsonObject(Map(
      "content" -> JsonString(post.content),
      "createdAt" -> JsonString(post.createdAt.toString)
    ))
  }

  implicit object FeedConverter extends JsonConverter[Feed] {
    override def convert(feed: Feed): JsonValue = JsonObject(Map(
      "user" -> feed.user.toJson,
      "posts" -> JsonArray(feed.posts.map(_.toJson))
    ))
  }



  // Testing
  val now = new Date(System.currentTimeMillis())
  val tom = User("tom", 21, "tburtgray@gmail.com")
  val feed = Feed(tom, List(
    Post("hello", now),
    Post("world", now)
  ))

  println(feed.toJson.stringify)
}
