package com.ikempf.advent5

import scala.annotation.tailrec

object Advent5 {

  def stepsToExit(jumps: String): Int =
    stepsToExit(jumps.split('\n').map(_.toInt))

  def stepsToExit(jumps: Seq[Int]): Int =
    runJumpInstructions(0, 0, jumps)

  @tailrec
  private def runJumpInstructions(step: Int, pos: Int, jumps: Seq[Int]): Int = {
    if (pos < 0 || pos >= jumps.length)
      step
    else {
      val jump = jumps(pos)
      val newJumps = jumps.updated(pos, jump + 1)
      runJumpInstructions(step + 1, pos + jump, newJumps)
    }
  }

}
