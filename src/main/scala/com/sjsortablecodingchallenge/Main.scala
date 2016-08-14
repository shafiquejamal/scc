package com.sjsortablecodingchallenge

import java.io.File

import com.sjsortablecodingchallenge.listing.ListingsReader
import com.sjsortablecodingchallenge.matching.output.ProductAndMatchingListingsWriter
import com.sjsortablecodingchallenge.matching.{MatchingAlgorithms, Matcher}
import com.sjsortablecodingchallenge.product.ProductsReader
import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]): Unit = {

    implicit val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()

    val productsPath = args(0)
    val listingsPath = args(1)
    val outputPath = new File(args(2))

    val products = ProductsReader.read(productsPath)
    val listings = ListingsReader.read(listingsPath)

    val matches = Matcher.matchProductsAndListings(products, listings, MatchingAlgorithms.matchUsingRegexsDashesSpaces)

    ProductAndMatchingListingsWriter.write(matches, outputPath)

    spark.stop()

  }


}
