import scala.io.Source
import Point.deserialisation

object Main {
  def main(args: Array[String]): Unit = {
    val src = Source.fromFile("./data/points.csv").getLines().drop(1).foreach(x => deserialisation(x).foreach( x => println(x)))
  }
}
