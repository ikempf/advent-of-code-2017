package com.ikempf.advent2

import fastparse.all._

object Advent2 {

  private val whitespace = P(" " | "\t")
  private val newline = P("\n")
  private val int = P(CharIn('0' to '9').rep(1).!.map(_.toInt))
  private val line = (int ~ whitespace.?).rep
  private val lines = Start ~ (line ~ newline).rep ~ End

  def checksum(spreadsheet: String): Int =
    checksum(lines.parse(spreadsheet).get.value)

  private def checksum(spreadsheet: Seq[Seq[Int]]): Int =
    spreadsheet
      .map(line => line.max - line.min)
      .sum

}
