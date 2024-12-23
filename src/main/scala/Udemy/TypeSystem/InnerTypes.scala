package Udemy.TypeSystem

object InnerTypes extends App {


  // Creating type safe API

  trait ItemLike {
    type Key
  }

  trait Item[K] extends ItemLike {
    type Key = K
  }
  trait IntItem extends Item[Int]

  trait StringItem extends Item[String]

  def get[ItemType <: ItemLike](key: ItemType#Key): ItemType = ???

  get[IntItem](42)
  get[StringItem]("42")
//  get[IntItem]("42") // does not compile, complains about input type
}
