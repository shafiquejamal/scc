package com.sjsortablecodingchallenge.matching.output

import java.io.{BufferedWriter, File, FileWriter}

import com.sjsortablecodingchallenge.matching.ProductAndMatchingListings
import play.api.libs.json.Json

object ProductAndMatchingListingsWriter {

  def write(productAndMatchingListings: Seq[ProductAndMatchingListings], filePath: File): Unit = {
    val bufferedWriter = new BufferedWriter(new FileWriter(filePath))
    bufferedWriter.write(productAndMatchingListings.map(foo => Json.toJson(foo)).mkString("\n"))
    bufferedWriter.close()
  }

}
