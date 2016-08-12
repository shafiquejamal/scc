package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.TestFixtures
import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.matching.MatchingAlgorithms._
import org.scalatest.{FlatSpec, ShouldMatchers}

class MatchingAlgorithmsUTest extends FlatSpec with ShouldMatchers with TestFixtures {

  "matching on manufacturer, and (in title) the model and family fields" should "succeed if the listing title contains " +
  "the text in all fields regardless of case" in new Products {
    val matchingListing = Listing("cyber-sHOt! lastest dsc-W310, brand new", "sOny", "USD", 10.99)
    val nonMatchingFamily = Listing("sOnY cyber-sHOOt for sale! lastest dsc-W310, brand new", "sOnY", "CAN", 10.99)
    val nonMatchingModel = Listing("sOnY cyber-sHOOt for sale! lastest dsc-W311, brand new", "sony", "KZT", 10.99)
    val nonMatchingManufacturer = Listing("cyber-sHOOt for sale! lastest dsc-W310, brand new", "sOnee", "GBP", 10.99)

    matchManufAndRestTitle(sonyCybershot, matchingListing) shouldBe true
    matchManufAndRestTitle(sonyCybershot, nonMatchingFamily) shouldBe false
    matchManufAndRestTitle(sonyCybershot, nonMatchingModel) shouldBe false
    matchManufAndRestTitle(sonyCybershot, nonMatchingManufacturer) shouldBe false
  }

  "matching on manufacturer and (in title) the model field" should "succeed if the listing title contains the text in " +
  "all fields regardless of case" in new Products {
    val matchingListing = Listing("lastest dsc-W310, brand new", "sOny", "USD", 10.99)
    val nonMatchingModel = Listing("sOnY for sale! lastest dsc-W311, brand new", "sony", "KZT", 10.99)
    val nonMatchingManufacturer = Listing(" for sale! lastest dsc-W310, brand new", "sOnee", "GBP", 10.99)

    matchManufAndModelInTitle(sonyCybershot, matchingListing) shouldBe true
    matchManufAndModelInTitle(sonyCybershot, nonMatchingModel) shouldBe false
    matchManufAndModelInTitle(sonyCybershot, nonMatchingManufacturer) shouldBe false
  }

  "matching on manufacturer and (in title) the model field" should "succeed if the listing title contains the text in " +
  "all fields regardless of case, but not if ' for ' is in the title"  in new Products {
    val matchingListing = Listing("lastest dsc-W310, brand new", "sOny", "USD", 10.99)
    val matchingListingButWithFor = Listing(" for sale! lastest dsc-W310, brand new", "sOny", "USD", 10.99)
    val nonMatchingModel = Listing("sOnY for sale! lastest dsc-W311, brand new", "sony", "KZT", 10.99)
    val nonMatchingManufacturer = Listing(" for sale! lastest dsc-W310, brand new", "sOnee", "GBP", 10.99)

    matchManufAndModelInTitleNoForInTitle(sonyCybershot, matchingListing) shouldBe true
    matchManufAndModelInTitleNoForInTitle(sonyCybershot, matchingListingButWithFor) shouldBe false
    matchManufAndModelInTitleNoForInTitle(sonyCybershot, nonMatchingModel) shouldBe false
    matchManufAndModelInTitleNoForInTitle(sonyCybershot, nonMatchingManufacturer) shouldBe false
  }

  "matching on manufacturer and (in title) the model field" should "succeed if the listing title contains the text in " +
  "all fields regardless of case, but not if ' for ' is in the title, and only if the word is not part of a larger word"  in
  new Products {
    val matchingListing = Listing("lastest dsc-W310, brand new", "sOny", "USD", 10.99)
    val matchingListingButWtthFor = Listing(" for sale! lastest dsc-W310, brand new", "Sony", "USD", 10.99)
    val nonMatchingModel = Listing("sOnY for sale! lastest dsc-W3101, brand new", "Sony", "KZT", 10.99)
    val nonMatchingManufacturer = Listing(" for sale! lastest dsc-W310, brand new", "fakeSony", "GBP", 10.99)

    matchUsingRegexs(sonyCybershot, matchingListing) shouldBe true
    matchUsingRegexs(sonyCybershot, matchingListingButWtthFor) shouldBe false
    matchUsingRegexs(sonyCybershot, nonMatchingModel) shouldBe false
    matchUsingRegexs(sonyCybershot, nonMatchingManufacturer) shouldBe false
  }

  "matching on manufacturer and (in title) the model field" should "succeed if the listing title contains the text in " +
  "all fields regardless of case, but not if ' for ' is in the title, and only if the word is not part of a larger word, " +
  "and replace dashes with space, remove dashes, and remove spaces in model" in new Products {
    val matchingListingNoChange = Listing("lastest dsc-W310, brand new", "sOny", "USD", 10.99)
    val matchingListingWithSpace = Listing("lastest dsc W310, brand new", "sOny", "USD", 10.99)
    val matchingListingRemoveDash = Listing("lastest dscW310, brand new", "sOny", "USD", 10.99)
    val matchingListingButWtthFor = Listing(" for sale! lastest dsc-W310, brand new", "Sony", "USD", 10.99)
    val nonMatchingModel = Listing("sOnY for sale! lastest dsc-W3101, brand new", "Sony", "KZT", 10.99)
    val nonMatchingManufacturer = Listing(" for sale! lastest dsc-W310, brand new", "fakeSony", "GBP", 10.99)

    matchUsingRegexsDealWithDashesSpaces(sonyCybershot, matchingListingNoChange) shouldBe true
    matchUsingRegexsDealWithDashesSpaces(sonyCybershot, matchingListingWithSpace) shouldBe true
    matchUsingRegexsDealWithDashesSpaces(sonyCybershot, matchingListingRemoveDash) shouldBe true
    matchUsingRegexsDealWithDashesSpaces(sonyCybershot, matchingListingButWtthFor) shouldBe false
    matchUsingRegexsDealWithDashesSpaces(sonyCybershot, nonMatchingModel) shouldBe false
    matchUsingRegexsDealWithDashesSpaces(sonyCybershot, nonMatchingManufacturer) shouldBe false

    matchUsingRegexsDealWithDashesSpaces(sonyCybershotNoDash, matchingListingNoChange) shouldBe true
    matchUsingRegexsDealWithDashesSpaces(sonyCybershotNoDash, matchingListingWithSpace) shouldBe true
    matchUsingRegexsDealWithDashesSpaces(sonyCybershotNoDash, matchingListingRemoveDash) shouldBe true
    matchUsingRegexsDealWithDashesSpaces(sonyCybershotNoDash, matchingListingButWtthFor) shouldBe false
    matchUsingRegexsDealWithDashesSpaces(sonyCybershotNoDash, nonMatchingModel) shouldBe false
    matchUsingRegexsDealWithDashesSpaces(sonyCybershotNoDash, nonMatchingManufacturer) shouldBe false
  }

}
