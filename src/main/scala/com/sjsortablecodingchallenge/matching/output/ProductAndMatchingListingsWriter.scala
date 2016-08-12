package com.sjsortablecodingchallenge.matching.output

import java.io.{BufferedWriter, File, FileWriter}

import com.sjsortablecodingchallenge.matching.ProductAndMatchingListings
import play.api.libs.json.Json

object ProductAndMatchingListingsWriter {

  def write(productAndMatchingListings: Seq[ProductAndMatchingListings], filePath: File): Unit = {
    val textForOutput = Json.toJson(productAndMatchingListings).toString.drop(1).dropRight(1)
    val bufferedWriter = new BufferedWriter(new FileWriter(filePath))
    bufferedWriter.write(textForOutput)
    bufferedWriter.close()
  }

}
