package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.listing.Listing

case class ProductAndMatchingListings(productName: String, listings: Seq[Listing])
