package com.ikempf.advent7

import fastparse.all._

object Advent7 {

  private val whitespace = P(" ")
  private val newline = P("\n")
  private val program = P(CharIn('a' to 'z')).rep.!
  private val weight = P("(") ~ CharIn('0' to '9').rep ~ P(")")
  private val holds = P("->")
  private val subPrograms = ((program ~ P(", ")).rep ~ program).map { case (init, last) => init :+ last }
  private val mappings = (whitespace ~ holds ~ whitespace ~ subPrograms).?.map(_.getOrElse(Seq.empty))
  private val line = program ~ whitespace ~ weight ~ mappings
  private val lines = Start ~ (line ~ newline).rep ~ End

  def findSubprogram(programs: String): Option[String] =
    findSubprogram(lines.parse(programs).get.value.toMap)

  def findSubprogram(programs: Map[String, Seq[String]]): Option[String] =
    programs.keys.find(notASubprogram(programs))

  private def notASubprogram(programs: Map[String, Seq[String]])(name: String) =
    !programs.values.exists(_.contains(name))

}
