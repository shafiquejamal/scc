package com.sjsortablecodingchallenge.listing

import org.apache.spark.sql.SparkSession
import org.scalatest.{FlatSpec, ShouldMatchers}

class ListingsReaderUTest extends FlatSpec with ShouldMatchers {

  "the reader" should "convert dataframe objects into datasets with type Listing" in {

    implicit val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()

    val listings =
      ListingsReader.read(spark.read.json("src/test/scala/com/sjsortablecodingchallenge/listing/TestListings.txt"))
    val expected =
      Seq(
        Listing("Canon PowerShot D10 12.1 MP Waterproof Digital Camera with 3x Optical Image Stabilized Zoom and 2.5-inch " +
                "LCD (Blue/Silver)", "Canon Canada", "CAD", 420.33),
        Listing("Genuine Samsung EB575152VU i9000 GalaxyS Battery", "Samsung", "CAD", 13.99d)
      )

    listings.collect() should contain theSameElementsAs expected

    spark.stop()


  }

}
