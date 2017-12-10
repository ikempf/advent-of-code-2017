package com.ikempf.advent8

import com.ikempf.advent8.model.Condition
import com.ikempf.advent8.model.Dec
import com.ikempf.advent8.model.EQ
import com.ikempf.advent8.model.GE
import com.ikempf.advent8.model.GT
import com.ikempf.advent8.model.Inc
import com.ikempf.advent8.model.LE
import com.ikempf.advent8.model.LT
import com.ikempf.advent8.model.NEQ
import com.ikempf.advent8.model.Program
import com.ikempf.advent8.model.Statement
import fastparse.all.CharIn
import fastparse.all.P
import fastparse.all._

object parsing {

  private val w = P(" ")
  private val registerP = P(CharIn('a' to 'z')).rep(1).!
  private val operation = P("inc" | "dec").!.map {
    case "inc" => Inc
    case "dec" => Dec
  }

  private val amountP = (P("-").!.? ~ P(CharIn('0' to '9')).rep(1).!)
      .map {
        case (None, amount) => amount.toInt
        case (_, amount) => -amount.toInt
      }

  private val operatorP = P(">=" | "<=" | "<" |  ">" | "==" | "!=").!
    .map {
      case "<" => LT
      case "<=" => LE
      case ">" => GT
      case ">=" => GE
      case "==" => EQ
      case "!=" => NEQ
    }

  private val condition = (P("if") ~ w ~ registerP ~ w ~ operatorP ~ w ~ amountP)
    .map {
      case (target, op, amount) => Condition(target, op, amount)
    }

  private val statementP = (registerP ~ w ~ operation ~ w ~ amountP ~ w ~ condition)
    .map {
      case (target, op, amount, cond) => Statement(target, op, amount, cond)
    }

  val programP = Start ~ (statementP ~ P("\n")).rep.map(Program.apply) ~ End

}
