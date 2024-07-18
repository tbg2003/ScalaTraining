
/** Defining custom errors */


// Often use case of case objects is custom errors

abstract class Error(name:String, errorCode:Int)

case object WeekdayNotFound extends Error("Weekday not found", 404)