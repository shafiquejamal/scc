package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.TestFixtures
import org.scalatest.LoneElement._
import org.scalatest.{FlatSpec, ShouldMatchers}

class MatcherUTest extends FlatSpec with ShouldMatchers with TestFixtures {

  "The matcher" should "associate listings with products that match, according to the algorithm passed in" in new Matches {

    val algorithm = MatchingAlgorithms.matchUsingRegexsDashesSpaces
    val listings = Seq(canonPowerShotCAD, genuineSamsungBattery, sonyCybershotListing1, sonyCybershotListing2)
    val products = Seq(sonyCybershot, samsungTl240)
    val expected = ProductAndMatchingListings(sonyCybershot.product_name, Seq(sonyCybershotListing1, sonyCybershotListing2))

    Matcher.matchProductsAndListings(products, listings, algorithm).loneElement.productName shouldBe expected.productName
    Matcher.matchProductsAndListings(products, listings, algorithm).loneElement.listings should
      contain theSameElementsAs expected.listings


  }

}
