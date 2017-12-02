package com.ikempf.advent1

import scala.annotation.tailrec

object Advent1 {

  def solve(captcha: String): Int = {
    val circular = captcha :+ captcha.head
    val intCaptcha = circular.map(_.asDigit).toList

    solve(intCaptcha)
  }

  private def solve(captcha: List[Int]): Int =
    captcha match {
      case a :: b :: t if a == b => a + solve(b +: t)
      case _ :: t                => solve(t)
      case Nil                   => 0
    }

  @tailrec
  private def solve(captcha: List[Int], acc: Int): Int =
    captcha match {
      case a :: b :: t if a == b => solve(b +: t, a + acc)
      case _ :: t                => solve(t, acc)
      case Nil                   => acc
    }

}
