// #####################
// MVP

//1
val numOfWatermelons: Int = 5
//2
if (numOfWatermelons > 5){
  println("Not possible to carry even with a bag")
} else if (numOfWatermelons > 3 && numOfWatermelons <= 5){
  println("You need a bag")
} else if (numOfWatermelons <= 3 && numOfWatermelons >= 0){
  println("You don't need a bag")
} else{
  println(s"$numOfWatermelons is an invalid number of watermelons")
}

//3
// S type interpolation is most appropriate here, you are dealing with integers so no need for advanced formatting

//4
val hasBag: Boolean = true

//5
if(hasBag && numOfWatermelons <= 5 && numOfWatermelons >= 0){
  println(s"You can carry $numOfWatermelons watermelons")
} else if (!hasBag && numOfWatermelons <= 3 && numOfWatermelons >= 0 ){
  println(s"You can carry $numOfWatermelons watermelons")
} else{
  println(s"cannot carry $numOfWatermelons watermelons")
}
//5 - without else if

if((hasBag && numOfWatermelons <= 5 || !hasBag && numOfWatermelons <=3) && numOfWatermelons >= 0){
  print(s"can carry $numOfWatermelons watermelons")
} else{
  print(s"You cannot carry $numOfWatermelons watermelons")
}


//6
// F type most appropriate - need to add additional formatting to the string
val conversionRate: Double = 1.397
println(f"Total price of watermelons is £${numOfWatermelons * conversionRate}%.2f")

// #####################
// Extension

//1

//2
if((true||false&&false))println("True") else println("False")
//3
if((true||false)&&false)println("True") else println("False")
//4
if(1<4 && 7 != 10 || 9+10 == 21) println("True") else println("False")

// #####################
// Research
val int1: Int = 4
val int2: Int = 5

val myString: String = if(int1 > int2) "yes" else "no"