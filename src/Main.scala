import Point.read_csv

object Main {
  def main(args: Array[String]): Unit = {
    val ListPoint = read_csv("./data/points.csv",header = true, ";")
    println(ListPoint)
  }
}
