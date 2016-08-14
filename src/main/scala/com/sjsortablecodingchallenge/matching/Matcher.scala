package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.product.Product

object Matcher {

  def matchProductsAndListings(
      products: Seq[Product],
      listings: Seq[Listing],
      matchingAlgorithm: (Product, Listing) => Boolean): Seq[ProductAndMatchingListings] = {
    products.map { product =>
      val matchingListings = listings.filter { listing =>
        matchingAlgorithm(product, listing)
      }
      ProductAndMatchingListings(product.product_name, matchingListings)
    }.filter( productAndListings =>  productAndListings.listings.nonEmpty )
  }

}
