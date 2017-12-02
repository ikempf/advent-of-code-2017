package com.ikempf.advent1

import com.ikempf.advent1.Advent1.solve
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class Advent1Test extends FlatSpec with Matchers {

  "ResolveCaptcha" should "solve captcha" in {
    solve("1122") should equal(3)
    solve("1111") should equal(4)
    solve("1234") should equal(0)
    solve("91212129") should equal(9)
  }

}
