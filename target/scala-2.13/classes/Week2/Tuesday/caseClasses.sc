// ##############################################################
// #                       Case Classes                         #
// ##############################################################

// Classes create blueprints for creating objects

// Case-Classes model immutable data, come with tons of boilerplate code, don't use new method

/** --- Extra Methods --- */
// - apply
// - unapply
// - hashcode and equals
// ...

/** --- Case Classes --- */

case class SuperHero(realName: String, heroName:String, weakness:String)

// Apply method - A constructor - Used to make objects
val NewSuperHero:SuperHero = SuperHero.apply(realName = "Clark Kent", heroName = "SuperMan", weakness = "Kryptonite")
val NewSuperHeroCopy:SuperHero = SuperHero(realName = "Clark Kent", heroName = "SuperMan", weakness = "Kryptonite")

// Can just write it out without apply method or new
val AnotherNewSuperHero:SuperHero = SuperHero(realName = "Clark Kentish", heroName = "SuperishMan", weakness = "Kryptonish")

// == operator compares the parameters of each object
NewSuperHero == NewSuperHeroCopy
NewSuperHero == AnotherNewSuperHero

// Copy method
val updatedName = NewSuperHero.copy(realName = "Clark Smith")
val updatedNameAndWeakness = NewSuperHero.copy(realName = "Clark Smith", weakness = "Animals")

// Unapply Method - An Extractor

/** --- Case Object --- */
case object Hulk

// ##############################################################
// #                          TASK                              #
// ##############################################################

case class Dog(name:String, breed:String, age:Double)
case class Cat(name:String, breed:String, age:Double)
case class Bird(name:String, breed:String, age:Double)

abstract class Shelter {
  val birds:List[Bird]
  val cats:List[Cat]
}
val dog1:Dog = Dog(name = "Buster", breed = "Bulldog", age = 3.5)
val dog2:Dog = Dog(name = "Charlie", breed = "Chiwawa", age = 3.5)
val dog3:Dog = Dog(name = "Dillon", breed = "Doberman", age = 3.5)
val dog4:Dog = Dog(name = "Simon", breed = "Spaniel", age = 3.5)

val cat1:Cat = Cat(name = "Buster", breed = "Bulldog", age = 3.5)
val cat2:Cat = Cat(name = "Charlie", breed = "Chiwawa", age = 3.5)
val cat3:Cat = Cat(name = "Dillon", breed = "Doberman", age = 3.5)
val cat4:Cat = Cat(name = "Simon", breed = "Spaniel", age = 3.5)

val bird1:Bird = Bird(name = "Buster", breed = "Bulldog", age = 3.5)
val bird2:Bird = Bird(name = "Charlie", breed = "Chiwawa", age = 3.5)
val bird3:Bird = Bird(name = "Dillon", breed = "Doberman", age = 3.5)
val bird4:Bird = Bird(name = "Simon", breed = "Spaniel", age = 3.5)

case class Kennel(name:String, dogs:List[Dog], cats:List[Cat], birds:List[Bird]){
  private var _dogs:List[Dog] = dogs
  private var _cats:List[Cat] = cats
  private var _birds:List[Bird] = birds
  def add_dog(newDog:Dog):List[Dog] = {
    _dogs = _dogs:+ newDog
    _dogs
  }
  def add_cat(newCat:Cat):List[Cat] = {
    _cats = _cats:+ newCat
    _cats
  }
  def add_bird(newBird:Bird):List[Bird] = {
    _birds = _birds:+ newBird
    _birds
  }
  def getDogs:List[Dog] = {
    _dogs
  }
  def getCats:List[Cat] = {
    _cats
  }
  def getBirds:List[Bird] = {
    _birds
  }
}
//case class Kennel(name:String, dogs:List[Dog]) extends Shelter {
//  override val birds: List[Bird] = List()
//  override val cats: List[Cat] = List()
//}

val myKennel:Kennel = Kennel(
  name = "Kennel",
  List(dog1),
  List(cat1),
  List(bird1))

val newKennelName:Kennel = myKennel.copy(name="Dog Hotel")

newKennelName.getDogs
newKennelName.getCats
newKennelName.getBirds


newKennelName.add_dog(dog2)
newKennelName.add_cat(cat2)
newKennelName.add_bird(bird2)




