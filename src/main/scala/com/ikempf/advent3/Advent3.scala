package com.ikempf.advent3

object Advent3 {

  private val NextSquareSizeIncrement = 2

  private val NextSquareStartPosition =
    (east _).tupled.andThen((south _).tupled)

  def distance(squareNumber: Int): Int = {
    val (i, j) = coordinates(squareNumber)
    Math.abs(i) + Math.abs(j)
  }

  def coordinates(squareNumber: Int): (Int, Int) =
    if (squareNumber <= 0)
      throw new IndexOutOfBoundsException("Indices start at 1")
    else if (squareNumber == 1)
      (0, 0)
    else {
      val tuple = squarePoints(NextSquareSizeIncrement, NextSquareStartPosition((0, 0)))
      println(tuple.take(squareNumber).toList)
      tuple.apply(squareNumber - 2)
    }

  private def squarePoints(edgeSize: Int, startPosition: (Int, Int)): Stream[(Int, Int)] = {
    val topRight = edgePoints(north, edgeSize, startPosition)
    val topLeft = edgePoints(west, edgeSize, topRight.last)
    val bottomLeft = edgePoints(south, edgeSize, topLeft.last)
    val bottomRight = edgePoints(east, edgeSize, bottomLeft.last)

    topRight.toStream
      .append(topLeft)
      .append(bottomLeft)
      .append(bottomRight)
      .append(squarePoints(edgeSize + NextSquareSizeIncrement, NextSquareStartPosition(bottomRight.last)))
  }

  private def edgePoints(direction: (Int, Int) => (Int, Int), edgeSize: Int, startPosition: (Int, Int)) =
    List.iterate(startPosition, edgeSize + 1)(direction.tupled).tail

  private def north(i: Int, j: Int) = (i, j + 1)
  private def west(i: Int, j: Int) = (i - 1, j)
  private def south(i: Int, j: Int) = (i, j - 1)
  private def east(i: Int, j: Int) = (i + 1, j)

}
