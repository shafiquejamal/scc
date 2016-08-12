package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.listing.Listing.listingsWrites
import play.api.libs.json._

case class ProductAndMatchingListings(productName: String, listings: Seq[Listing])

object ProductAndMatchingListings {

  implicit val ProductAndMatchingListingsWrites = new Writes[ProductAndMatchingListings] {
    def writes(productAndMatchingListings:ProductAndMatchingListings) =
      Json.obj(
        "product_name" -> productAndMatchingListings.productName,
        "listings" -> productAndMatchingListings.listings
      )
  }

}
