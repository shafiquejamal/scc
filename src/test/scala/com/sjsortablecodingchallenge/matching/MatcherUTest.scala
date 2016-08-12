package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.TestFixtures
import org.apache.spark.sql.SparkSession
import org.scalatest.LoneElement._
import org.scalatest.{FlatSpec, ShouldMatchers}

class MatcherUTest extends FlatSpec with ShouldMatchers with TestFixtures {

  "The matcher" should "associate listings with products that match, according to the algorithm passed in" in new Matches {

    val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()
    import spark.implicits._

    val algorithm = MatchingAlgorithms.matchUsingRegexsDashesSpaces
    val listings = Seq(canonPowerShotCAD, genuineSamsungBattery, sonyCybershotListing1, sonyCybershotListing2).toDS()
    val products = Seq(sonyCybershot, samsungTl240).toDS
    val expected = ProductAndMatchingListings(sonyCybershot.product_name, Seq(sonyCybershotListing1, sonyCybershotListing2))

    Matcher.matchProductsAndListings(products, listings, algorithm).loneElement.productName shouldBe expected.productName
    Matcher.matchProductsAndListings(products, listings, algorithm).loneElement.listings should
      contain theSameElementsAs expected.listings

    spark.stop()

  }

}
