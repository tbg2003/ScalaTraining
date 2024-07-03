// Conditional if/else
// #######################

val weather: String = "Rain" +
  ""
val season: String = "winter"

//if(weather == "cold" && season == "winter"){
//  println(s"It's $weather so take a winter coat")
//} else{
//  println("Don't worry about that coat")
//}

if (weather.toLowerCase() == "cold") {
  println("take a coat")
} else if(weather.toLowerCase() == "rain"){
  println("take an umbrella")
} else{
  println("Take nothing")
}

// #######################
// Task 1

val grade: Double = 80.46

if (grade >= 90 && grade <= 100){println("Grade A achieved")}
else if (grade >= 80){println("Grade B achieved")}
else if (grade >= 70){println("Grade C achieved")}
else if (grade >= 60){println("Grade D achieved")}
else if (grade >= 50){println("Grade E achieved")}
else if (grade >= 50){println("Grade F achieved")}
else {println(s"$grade is an invalid mark")}

// #######################
// Task 2

val age: Int = 15

if (age >= 18){println("You can watch all movies")}
else if(age >= 15) {println("you can watch all movies rated 15, 12A, PG & U")}
else if (age >= 12) {println("you can watch all movies rated 12A, PG, U")}
else if (age >= 8) {println("you can watch all movies rated PG or U")}
else if (age >= 4) {println("you can watch all movies rated U")}
else if (age < 4 && age >= 0){println("Your tiny undeveloped brain cannot understand movies, so you're not allowed to watch any")}
else{ println(s"$age is an invalid age")}
