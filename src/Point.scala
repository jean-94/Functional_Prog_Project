import scala.annotation.tailrec
import scala.io.Source

case class Point private(x: Float, y: Float, z: Option[Float] = None) {}

object Point {
  def newPoint(x: Float, y: Float): Point = Point(x,y)
  def newPoint(x: Float, y: Float, z: Float): Point = Point(x,y,Some(z))

  def deserialization(line: String,sep: String = ","): Option[Point] = {
    val pattern2 = ("""^[+-]?[0-9]+\.?[0-9]*\""" +
      sep + """[+-]?[0-9]+\.?[0-9]*$""").r
    val pattern3 = ("""^[+-]?[0-9]+\.?[0-9]*\""" +
      sep + """[+-]?[0-9]+\.?[0-9]*\""" +
      sep + """[+-]?[0-9]+\.?[0-9]*$""").r
    line match {
      case pattern2() => Some(newPoint(
        line.split(sep)(0).toFloat,
        line.split(sep)(1).toFloat
      ))
      case pattern3() => Some(newPoint(
        line.split(sep)(0).toFloat,
        line.split(sep)(1).toFloat,
        line.split(sep)(2).toFloat
      ))
      case _ => None
    }
  }

  def read_csv(path : String, header: Boolean = false,sep: String = ","): List[Option[Point]] ={
    val source = Source.fromFile(path)
    val fileContents = source.getLines.toList
    source.close
    val lines = (fileContents, header) match {
      case (_,false) => fileContents
      case (_::tail,_) => tail
    }

    @tailrec
    def read_csv_aux(lines: List[String], acc: List[Option[Point]] = Nil): List[Option[Point]] = lines match{
      case Nil => acc
      case h::t => read_csv_aux(t,deserialization(h,sep)::acc)
    }

    read_csv_aux(lines).reverse
  }
}
