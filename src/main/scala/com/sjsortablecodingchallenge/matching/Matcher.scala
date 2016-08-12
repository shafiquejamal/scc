package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.product.Product
import org.apache.spark.sql.Dataset

object Matcher {

  def matchProductsAndListings(
      products: Dataset[Product],
      listings: Dataset[Listing],
      matchingAlgorithm: (Product, Listing) => Boolean): Seq[ProductAndMatchingListings] = {
    products.collect().toSeq.map { product =>
      val matchingListings = listings.collect().toSeq.filter { listing =>
        matchingAlgorithm(product, listing)
      }
      ProductAndMatchingListings(product.product_name, matchingListings)
    }.filter( productAndListings =>  productAndListings.listings.nonEmpty )
  }

}
