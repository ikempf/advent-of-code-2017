package com.ikempf.advent3

import com.ikempf.advent3.Advent3.coordinates
import com.ikempf.advent3.Advent3.distance
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class Advent3Test extends FlatSpec with Matchers {

  "Coordinates" should "compute coordinates of given square number" in {
    coordinates(2) should equal((1, 0))
    coordinates(3) should equal((1, 1))
    coordinates(8) should equal((0, -1))
    coordinates(14) should equal((1, 2))
    coordinates(23) should equal((0, -2))
  }

  "Distance" should "compute manhattan distance of given square number" in {
    distance(2) should equal(1)
    distance(3) should equal(2)
    distance(8) should equal(1)
    distance(14) should equal(3)
    distance(23) should equal(2)
    distance(49) should equal(6)
  }

}
