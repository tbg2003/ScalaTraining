package Udemy.TypeSystem

object Variance extends App {
  def spacer = println(
    """
      |##################################################
      |""".stripMargin)
  spacer

  trait Animal
  class Dog extends Animal
  class Cat extends Animal
  class Crocodile extends Animal

  // what is variance?
  // "inheritance" - type substitution of generics

  class Cage[T]

  class CovarCage[+T]
  val covarCage: CovarCage[Animal] = new CovarCage[Cat]

  class InvarCage[T]
//  val invarCage: InvarCage[Animal] = new InvarCage[Cat] // does not compile - different types

  class ContraCage[-T]
  val contraCage: ContraCage[Cat] = new ContraCage[Animal]

  class InvariantCage[T](val animal: T)
  class CovariantCage[+T](val animal: T)




  // METHOD ARGUMENTS ARE IN CONTRAVARIANT POSITION

  // return types
  class PetShop[-T] {
    def get[S<:T](isItAPuppy: Boolean, defaultAnimal: S): S = defaultAnimal
  }

  // RETURN TYPES ARE IN COVARIANT POSITION



  class Vehicle
  class Bike extends Vehicle
  class Car extends Vehicle
  class Van extends Vehicle

  /**
   * INVARIANT
   */
  class IParking[T](things: List[T]) {
    def park(vehicle: T): IParking[T] = new IParking[T](things :+ vehicle)
    def impound(vehicles: List[T]): IParking[T] = new IParking[T](things.diff(vehicles))
    def checkVehicles(conditions: String): List[T] = {
      conditions match {
        case "bike" => things.filter(_.isInstanceOf[Bike])
        case "car" => things.filter(_.isInstanceOf[Car])
        case "van" => things.filter(_.isInstanceOf[Van])
        case _ => things
      }
    }
  }

  /**
   * Covariant
   */
  class CParking[+T](things: List[T]) {
    def park[S >: T](vehicle: S): CParking[S] = ???
    def impound[S >: T](vehicles: List[S]): CParking[S] = ???
    def checkVehicles(conditions: String): List[T] = ???
  }

  /**
   * Contravariant
   */
  class XParking[-T](things: List[T]) {
    def park(vehicle: T): XParking[T] = ???
    def impound(vehicles: List[T]): XParking[T] = ???
    def checkVehicles[S <: T](conditions: String): List[S] = ???
  }


  /*
     --- RULE OF THUMB ---
    - use covariance = COLLECTION OF THINGS
    - use contravariance = GROUP OF ACTIONS
   */


  // Using an Invariant List
  // making a monad
  /* --- Invariant ---
      def flatMap[s](f: T => IParking[S]): IParking[S] = ???
   */

  class IList[T]

  /**
   * Covariant with IList
   */
  class CParking2[+T](things: IList[T]) {
    def park[S >: T](vehicle: S): CParking2[S] = ???
    def impound[S >: T](vehicles: IList[S]): CParking2[S] = ???
    def checkVehicles[S >: T](conditions: String): IList[S] = ???

    def flatMap[S](f: T => CParking2[S]): CParking2[S] = ???
  }

  /**
   * Contravariant with IList
   */
  class XParking2[-T](things: IList[T]) {
    def park(vehicle: T): XParking2[T] = ???
    def impound[S <: T](vehicles: IList[S]): XParking2[S] = ???
    def checkVehicles[S <: T](conditions: String): IList[S] = ???

    def flatMap[R <: T, S](f: R => XParking2[S]): XParking2[S] = ???
  }
}
