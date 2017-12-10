package com.ikempf.advent8

import com.ikempf.advent8.model._
import com.ikempf.advent8.Advent8.eval
import com.ikempf.advent8.Advent8.highestRegister
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class Advent8Test extends FlatSpec with Matchers {

  "Eval" should "evaluate given condition" in {
    eval(Condition("a", LT, 5))(Map.empty)._2 should be(true)
    eval(Condition("a", LT, 5))(Map("a" -> 3))._2 should be(true)
    eval(Condition("a", LT, 5))(Map("a" -> 8))._2 should be(false)
    eval(Condition("a", EQ, 8))(Map("a" -> 8))._2 should be(true)
  }

  "Execute" should "execute given statement" in {
    val stmnt = Statement("c", Inc, -30, Condition("a", LT, 5))

    Advent8.execute(stmnt)(Map.empty) should equal(Map("a" -> 0, "c" -> -30))
  }

  "Run" should "run program" in {
    val program = Program(List(
      Statement("b", Inc, 5, Condition("a", GT, 1)),
      Statement("a", Inc, 1, Condition("b", LT, 5)),
      Statement("c", Dec, -10, Condition("a", GE, 1)),
      Statement("c", Inc, -20, Condition("c", EQ, 10))
    ))

    Advent8.run(program) should equal(Map(
      "c" -> -10,
      "a" -> 1,
      "b" -> 0)
    )
  }

  "HighestRegister" should "find highest valued register after running given program" in {
    val program = Program(List(
      Statement("b", Inc, 5, Condition("a", GT, 1)),
      Statement("a", Inc, 1, Condition("b", LT, 5)),
      Statement("c", Inc, -10, Condition("a", GE, 1)),
      Statement("c", Inc, -20, Condition("c", EQ, 10))
    ))

    highestRegister(program) should equal(("a", 1))
  }

  it should "find highest valued register after running given string program" in {
    val program = """b inc 5 if a > 1
                    |a inc 1 if b < 5
                    |c dec -10 if a >= 1
                    |c inc -20 if c == 10
                    |""".stripMargin

    highestRegister(program) should equal(("a", 1))
  }

}
