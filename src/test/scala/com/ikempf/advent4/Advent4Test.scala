package com.ikempf.advent4

import com.ikempf.advent4.Advent4.isValidPassword
import com.ikempf.advent4.Advent4.validPassphraseCount
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class Advent4Test extends FlatSpec with Matchers {

  "ValidPasswordCount" should "count all valid passwords among given set" in {
    validPassphraseCount(List("aa bb aa", "aa aa", "aa bb")) should be(1)
    validPassphraseCount(List("aa bb cc", "aa aa", "aa bb")) should be(2)
    validPassphraseCount(
      """aa bb cc
        |aa aa bb
        |aa bb
        |aa aa
        |aa bb""".stripMargin) should equal(3)
  }

  "IsValidPassword" should "check password validity" in {
    isValidPassword("aa bb cc dd ee") should be(true)
    isValidPassword("aa bb cc dd aa") should be(false)
    isValidPassword("aa bb cc dd aaa") should be(true)
  }

}
