package com.ikempf.advent2

import com.ikempf.advent2.Advent2.checksum
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class Advent2Test extends FlatSpec with Matchers {

  "Checksum" should "compute the checksum of the given spreadsheet" in {
    val input =
      """|5 1 9 5
         |7 5 3
         |2 4 6 8
         |""".stripMargin

    checksum(input) should equal(18)
  }

}
