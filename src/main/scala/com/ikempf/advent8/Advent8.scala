package com.ikempf.advent8
import model._
import parsing._

// TODO: Use state monad
object Advent8 {

  def highestRegister(program: String): (String, Int) =
    highestRegister(programP.parse(program).get.value)

  def highestRegister(program: Program): (String, Int) =
    run(program).maxBy(_._2)

  def run(program: Program): Map[String, Int] =
    program
      .stmnts
      .foldLeft(Map.empty[String, Int])((acc, stmnt) => execute(stmnt)(acc))

  def execute(stmnt: Statement)(registerValues: Map[String, Int]): Map[String, Int] =
    stmnt.operation match {
      case Inc => execute(stmnt.target, stmnt.amount, stmnt.cond)(registerValues)
      case Dec => execute(stmnt.target, -stmnt.amount, stmnt.cond)(registerValues)
    }

  private def execute(target: String, amount: Int, cond: Condition)(registerValues: Map[String, Int]) = {
    val (updatedRegisters, condRes) = eval(cond)(registerValues)

    if (condRes) {
      val current = currentValue(target, updatedRegisters)
      updatedRegisters + (target -> (current + amount))
    } else {
      val current = currentValue(target, updatedRegisters)
      updatedRegisters + (target -> current)
    }
  }

  private def currentValue(target: String, registerValues: Map[String, Int], default: Int = 0) =
    registerValues.getOrElse(target, default)

  def eval(condition: Condition)(registerValues: Map[String, Int]): (Map[String, Int], Boolean) = {
    val current = currentValue(condition.target, registerValues)
    val updatedRegisters = registerValues + (condition.target -> current)

    val res = condition.op match {
      case LT => current < condition.amount
      case GT => current > condition.amount
      case LE => current <= condition.amount
      case GE => current >= condition.amount
      case EQ => current == condition.amount
      case NEQ => current != condition.amount
    }

    (updatedRegisters, res)
  }


}
