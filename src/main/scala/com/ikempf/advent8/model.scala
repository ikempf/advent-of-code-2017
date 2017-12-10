package com.ikempf.advent8

object model {

  case class Program(stmnts: Seq[Statement])

  case class Statement(target: String, operation: Operation, amount: Int, cond: Condition)

  sealed trait Operation
  case object Inc extends Operation
  case object Dec extends Operation

  case class Condition(target: String, op: Operator, amount: Int)

  sealed trait Operator
  case object LT extends Operator
  case object GT extends Operator
  case object LE extends Operator
  case object GE extends Operator
  case object EQ extends Operator
  case object NEQ extends Operator

}
