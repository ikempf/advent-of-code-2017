package com.ikempf.advent6

import com.ikempf.advent6.Advent6.redistributionCycleCount
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class Advent6Test extends FlatSpec with Matchers {

  "RedestributionCycleCount" should "compute the necessary cycles until an already seen configuration is produced" in {
    redistributionCycleCount(List(0, 2, 7, 0)) should equal(5)
  }

}
