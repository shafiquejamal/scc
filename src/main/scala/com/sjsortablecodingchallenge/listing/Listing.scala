package com.sjsortablecodingchallenge.listing

import play.api.libs.json._

case class Listing(title: String, manufacturer: String, currency: String, price: String)

object Listing {

  implicit val listingsWrites = new Writes[Listing] {
    def writes(listing:Listing) =
      Json.obj(
        "title" -> listing.title,
        "manufacturer" -> listing.manufacturer,
        "currency" -> listing.currency,
        "price" -> listing.price
      )
  }

}
