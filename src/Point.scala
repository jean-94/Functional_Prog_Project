

case class Point private(x: Float, y: Float, z: Option[Float] = None) {}

object Point {
  def newPoint(x: Float, y: Float): Point = Point(x,y)
  def newPoint(x: Float, y: Float, z: Float): Point = Point(x,y,Some(z))

  def deserialization(line: String,sep: String = ","): Either[String,Point] = {
    val pattern2 = ("""^[+-]?[0-9]+\.?[0-9]*\""" +
      sep + """[+-]?[0-9]+\.?[0-9]*$""").r
    val pattern3 = ("""^[+-]?[0-9]+\.?[0-9]*\""" +
      sep + """[+-]?[0-9]+\.?[0-9]*\""" +
      sep + """[+-]?[0-9]+\.?[0-9]*$""").r
    line match {
      case pattern2() => Right(newPoint(
        line.split(sep)(0).toFloat,
        line.split(sep)(1).toFloat
      ))
      case pattern3() => Right(newPoint(
        line.split(sep)(0).toFloat,
        line.split(sep)(1).toFloat,
        line.split(sep)(2).toFloat
      ))
      case _ => Left("No point could be built from the line: " + line)
    }
  }
}
