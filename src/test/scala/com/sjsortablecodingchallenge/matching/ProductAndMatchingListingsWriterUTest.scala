package com.sjsortablecodingchallenge.matching

import java.io.File.createTempFile

import com.sjsortablecodingchallenge.TestFixtures
import org.scalatest.{FlatSpec, ShouldMatchers}

class ProductAndMatchingListingsWriterUTest extends FlatSpec with ShouldMatchers with TestFixtures {

  "The writer" should "write the output in the specified format" in new Matches {

    val outputFile = createTempFile("output.json", ".json")

    ProductAndMatchingListingsWriter.write(Seq(productAndMatchingListings1, productAndMatchingListings2), outputFile)

    val text = scala.io.Source.fromFile(outputFile).mkString
    text shouldEqual expectedJsonAsString.drop(1).dropRight(1)

    outputFile.delete()
  }

}
