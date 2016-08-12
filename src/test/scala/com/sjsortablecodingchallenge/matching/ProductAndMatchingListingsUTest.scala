package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.TestFixtures
import org.scalatest.{FlatSpec, ShouldMatchers}
import play.api.libs.json.Json

class ProductAndMatchingListingsUTest extends FlatSpec with ShouldMatchers with TestFixtures {

  "Converting the matches to Json" should "yield the output in the required json format" in new Matches {
    Json.toJson(Seq(productAndMatchingListings1, productAndMatchingListings2)).toString shouldEqual expectedJsonAsString
  }

}
