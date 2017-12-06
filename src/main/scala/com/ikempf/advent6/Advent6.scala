package com.ikempf.advent6

import scala.annotation.tailrec

object Advent6 {

  def redistributionCycleCount(banks: List[Int]): Int =
    redistributionCycleCount(banks, List.empty, 1)

  @tailrec
  def redistributionCycleCount(banks: List[Int], seenConfigurations: List[List[Int]], cycles: Int): Int = {
    val (bankBlocks, bankIndex) = findHighestBank(banks)
    val blockRedistribution = redistributeBlocks(bankBlocks, banks.length, bankIndex + 1)
    val emptiedHighestBank = emptyBank(banks, bankIndex)
    val redistributedBanks = redistributeToBanks(emptiedHighestBank, bankIndex, blockRedistribution)

    if (seenConfigurations.contains(redistributedBanks))
      cycles
    else
      redistributionCycleCount(redistributedBanks, redistributedBanks +: seenConfigurations, cycles + 1)
  }

  private def findHighestBank(banks: List[Int]) =
    banks.zipWithIndex.maxBy(_._1)

  private def emptyBank(banks: List[Int], bankIndex: Int) =
    banks.updated(bankIndex, 0)

  private def redistributeBlocks(bankBlocks: Int, bankCount: Int, startingIndex: Int) =
    distribute(bankBlocks, List.fill(bankCount)(0), startingIndex)

  def distribute(blocksToDistribute: Int, distribution: List[Int], bankIndex: Int): List[Int] =
    if (blocksToDistribute <= 0)
      distribution
    else {
      val effectiveBankIndex = bankIndex % distribution.length
      val redistributed = distribution.updated(effectiveBankIndex, distribution(effectiveBankIndex) + 1)
      distribute(blocksToDistribute - 1, redistributed, effectiveBankIndex + 1)
    }

  private def redistributeToBanks(banks: List[Int], bankIndex: Int, blockRedistribution: List[Int]) =
    banks
      .zip(blockRedistribution)
      .map {
        case (blocks, redistributedBlocks) => blocks + redistributedBlocks
      }

}
