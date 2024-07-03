// String Interpolation
// Research RegEx in string interpolation

ç
// S interpolation

val name: String = "Tom"
val number: Int = 12

println(s"Hey $name")
println(s"3 x 3 = ${3*3}")
println(s"$number x $number = ${number * number}")

// #########################
// F interpolation
// %f - floating point number
// %d - decimal
// %i - integer
// ...
val pi: Double = 3.1415926535897
println(f"Pi to 2dp is: $pi%.2f")
println(f"Pi to 4dp is: $pi%.4f")
println(f"Pi to 2dp with width of 10 is: $pi%10.4f")

printf("'%s'", name)
printf("'%s'", name)
printf("'%-16s'", name)

// #########################
// Raw
println("Hello \nTom")
println(raw"Hello \nTom")

// #########################
// Tasks
//1
val name1: String = "John"
val name2: String = "Jane"
println(s"$name1 is older than $name2")

//2
val age1: Double = 100.57
val age2: Double = 76.94

println(f"$name1%s is $age1%.1f years old \n$name2%s is $age2%5.1f years old")
println(f"$name1%s is ${age1-age2}%5.1f years older than $age2%s")

//3
println(raw"\n\n\n\n\n\n\n\nsameline\n\n")

//4s
val height: Double = 2.89
printf("Apparently %s is '%.1fm' tall", name, height)






