package com.ikempf.advent5

import com.ikempf.advent5.Advent5.stepsToExit
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class Advent5Test extends FlatSpec with Matchers {

  "StepsToExit" should "compute number of steps to exit" in {
    stepsToExit(List(0, 3, 0, 1, -3)) should equal(5)
    stepsToExit("""0
                  |3
                  |0
                  |1
                  |-3""".stripMargin) should equal(5)
  }

}
