package com.ikempf.advent7

import com.ikempf.advent7.Advent7.findSubprogram
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class Advent7Test extends FlatSpec with Matchers {

    "Findsubprogram" should "should find bottom program of the tower given a flat representation" in {
      val flatProgramTree = Map(
        "pbga" -> List.empty,
        "xhth" -> List.empty,
        "ebii" -> List.empty,
        "havc" -> List.empty,
        "ktlj" -> List.empty,
        "fwft" -> List("ktlj", "cntj", "xhth"),
        "qoyq" -> List.empty,
        "padx" -> List("pbga", "havc", "qoyq"),
        "tknk" -> List("ugml", "padx", "fwft"),
        "jptl" -> List.empty,
        "ugml" -> List("gyxo", "ebii", "jptl"),
        "gyxo" -> List.empty,
        "cntj" -> List.empty)

      findSubprogram(flatProgramTree) should equal(Some("tknk"))
    }

  "it" should "should find bottom program of the tower textual representation" in {
    val input =
      """pbga (66)
        |xhth (57)
        |ebii (61)
        |havc (66)
        |ktlj (57)
        |fwft (72) -> ktlj, cntj, xhth
        |qoyq (66)
        |padx (45) -> pbga, havc, qoyq
        |tknk (41) -> ugml, padx, fwft
        |jptl (61)
        |ugml (68) -> gyxo, ebii, jptl
        |gyxo (61)
        |cntj (57)
        |""".stripMargin

        findSubprogram(input) should equal(Some("tknk"))
  }

}
