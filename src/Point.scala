case class Point(val x: Float, val y: Float, val z: Option[Float] = None) {

}

object Point {
  def deserialisation(info: String): Option[Point] = {

    val pattern = """^[-+]?[0-9]+.?[0-9]*\;[-+]?[0-9]+.?[0-9]*\;?[-+]?[0-9]+.?[0-9]*$""".r

    info match {
      case pattern(_*) if (info.split(";").length == 2) => Some(new Point(info.split(";")(0).toFloat, info.split(";")(1).toFloat))
      case pattern(_*) if (info.split(";").length == 3) => Some(new Point(info.split(";")(0).toFloat, info.split(";")(1).toFloat, info.split(";")(2).toFloatOption))
      case _ => None
    }

  }

}
