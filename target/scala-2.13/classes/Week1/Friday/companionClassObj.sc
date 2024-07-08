// ###################################
// #  Companion Classes and Objects  #
// ###################################


/** WITHOUT Companion Objects */

class Book(val title:String,
           val author:String,
           val year:Int
          ){

  def description(): String = s"$title was written by $author in $year"
}

val book1 = new Book(title = "1984", author = "George Orwell", year = 1949)
val book2 = new Book(title = "To Kill a Mockingbird", author = "Harper Lee", year = 1960)

println(book1.description())
println(book2.description())



/**        ||||        */
/**        ||||        */
/**        ||||        */
/**        ||||        */
/**     \\\||||///     */
/**       \\||//       */
/**         \/         */



/** WITH Companion Objects */

class Book(val title:String,
           val author:String,
           val year:Int){
  private var _numOfOwners = 0
  def description():String = s"$title was written by $author in $year"
}

object Book{
  def apply(title:String,
            author:String,
            year:Int): Book = new Book(title, author, year)
  def fromTuple(tuple: (String, String, Int)):Book = new Book(tuple._1, tuple._2, tuple._3)
}

val book1 = Book("1984", "George Orwel", 1949)
val book2 = Book.fromTuple(("To Kill a Mockingbird", "Harper Lee", 1960))
