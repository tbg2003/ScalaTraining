sealed trait Sex
  case object Male extends Sex
  case object Female extends Sex


class Person(val firstName:String,
             var lastName:String,
             val sex: Sex,
             val mother: Option[Person] = None,
             val father: Option[Person] = None,
            ){
  private var _children: List[Person] = List()
  private var _spouse: Option[Person] = None

  def fullName: String = s"$firstName $lastName"

  def parents: String = {
    val motherName = mother.map(_.fullName).getOrElse("Unkown")
    val fatherName = father.map(_.fullName).getOrElse("Unkown")
    s"Mother: $motherName and Father: $fatherName"
  }

  def addChild(child: Person):Unit = {
    _children = child +: _children
  }

  def setSpouse(partner: Person): Unit = {
    _spouse = Some(partner)
    partner._spouse = Some(this)
    if(_spouse.map(_.sex).contains(Male)) this.lastName = partner.lastName else partner.lastName = lastName
  }

  def LastName: String = s"${this.lastName}"

  def Spouse: Option[Person] = _spouse
}


object Person{
  def apply(firstName: String,
            lastName: String,
            sex:Sex,
            mother:Option[Person]=None,
            father:Option[Person]=None,
           ): Person = new Person(firstName, lastName, sex, mother, father)
  def addChild(father:Person,
               mother:Person,
               firstName:String,
               sex:Sex): Person = {
    val child = new Person(firstName, father.lastName, sex, Some(mother), Some(father))
    father.addChild(child)
    mother.addChild(child)
    child
  }
}

// extends App makes it executable - provides a main function
object FamilyTree extends App{
  val father1 = Person("John", "Smith", Male)
  val mother1 = Person("Jane", "Doe", Female)
  father1.setSpouse(mother1)
  val child1 = Person.addChild(father1, mother1, "Aisha", Female)


  val father2 = Person("Alex", "Grey", Male)
  val mother2 = Person("Anna", "Black", Female)
  father2.setSpouse(mother2)
  val child2 = Person.addChild(father2, mother2, "Jimmy", Male)


  child2.setSpouse(child1)
  val child3 = Person.addChild(child1, child2, "Isla", Female)

  println(s"Parents of ${child3.fullName} are: ${child3.parents}")
  println(s"Parents of ${child1.fullName} are: ${child1.parents}")
  println(s"Parents of ${child2.fullName} are: ${child2.parents}")


}


FamilyTree.main(Array())