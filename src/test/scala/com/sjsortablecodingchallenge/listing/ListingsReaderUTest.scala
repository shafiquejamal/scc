package com.sjsortablecodingchallenge.listing

import com.sjsortablecodingchallenge.TestFixtures
import org.apache.spark.sql.SparkSession
import org.scalatest.{FlatSpec, ShouldMatchers}

class ListingsReaderUTest extends FlatSpec with ShouldMatchers with TestFixtures {

  "the reader" should "convert dataframe objects into datasets with type Listing" in new Listings {

    implicit val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()

    val listings = ListingsReader.read("src/test/scala/com/sjsortablecodingchallenge/listing/UTestListings.txt")
    val expected = Seq(canonPowerShotCAD, genuineSamsungBattery)

    listings should contain theSameElementsAs expected

    spark.stop()


  }

}
