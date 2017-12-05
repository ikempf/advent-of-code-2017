package com.ikempf.advent4

object Advent4 {

  def validPassphraseCount(passwords: String): Int =
    validPassphraseCount(passwords.split("\n"))

  def validPassphraseCount(passwords: Seq[String]): Int =
    passwords.count(isValidPassword)

  def isValidPassword(password: String): Boolean =
    !password
      .split(' ')
      .groupBy(identity)
      .exists(_._2.length > 1)

}
