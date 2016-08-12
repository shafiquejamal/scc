package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.TestFixtures
import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.matching.MatchingAlgorithms._
import org.scalatest.{FlatSpec, ShouldMatchers}

class MatchingAlgorithmsUTest extends FlatSpec with ShouldMatchers with TestFixtures {

  "matching on manufacturer and (in title) the model field" should "succeed if the listing title contains the text in " +
  "all fields regardless of case, but not if ' for ' is in the title, and only if the word is not part of a larger word, " +
  "and replace dashes with space, remove dashes, and remove spaces in model" in new Products {
    val matchingListingNoChange = Listing("lastest dsc-W310, brand new", "sOny", "USD", "10.99")
    val matchingListingWithSpace = Listing("lastest dsc W310, brand new", "sOny", "USD", "10.99")
    val matchingListingRemoveDash = Listing("lastest dscW310, brand new", "sOny", "USD", "10.99")
    val matchingListingButWtthFor = Listing(" for sale! lastest dsc-W310, brand new", "Sony", "USD", "10.99")
    val nonMatchingModel = Listing("sOnY for sale! lastest dsc-W3101, brand new", "Sony", "KZT", "10.99")
    val nonMatchingManufacturer = Listing(" for sale! lastest dsc-W310, brand new", "fakeSony", "GBP", "10.99")

    matchUsingRegexsDashesSpaces(sonyCybershot, matchingListingNoChange) shouldBe true
    matchUsingRegexsDashesSpaces(sonyCybershot, matchingListingWithSpace) shouldBe true
    matchUsingRegexsDashesSpaces(sonyCybershot, matchingListingRemoveDash) shouldBe true
    matchUsingRegexsDashesSpaces(sonyCybershot, matchingListingButWtthFor) shouldBe false
    matchUsingRegexsDashesSpaces(sonyCybershot, nonMatchingModel) shouldBe false
    matchUsingRegexsDashesSpaces(sonyCybershot, nonMatchingManufacturer) shouldBe false

    matchUsingRegexsDashesSpaces(sonyCybershotNoDash, matchingListingNoChange) shouldBe true
    matchUsingRegexsDashesSpaces(sonyCybershotNoDash, matchingListingWithSpace) shouldBe true
    matchUsingRegexsDashesSpaces(sonyCybershotNoDash, matchingListingRemoveDash) shouldBe true
    matchUsingRegexsDashesSpaces(sonyCybershotNoDash, matchingListingButWtthFor) shouldBe false
    matchUsingRegexsDashesSpaces(sonyCybershotNoDash, nonMatchingModel) shouldBe false
    matchUsingRegexsDashesSpaces(sonyCybershotNoDash, nonMatchingManufacturer) shouldBe false
  }

}
