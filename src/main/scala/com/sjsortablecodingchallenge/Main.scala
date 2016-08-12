package com.sjsortablecodingchallenge

import com.sjsortablecodingchallenge.listing.ListingsReader
import com.sjsortablecodingchallenge.product.ProductsReader
import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]): Unit = {

    implicit val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()

    val productsPath = args(0)
    val listingsPath = args(1)
    val outputPath = args(2)

    val listings = ListingsReader.read(listingsPath)
    val products = ProductsReader.read(productsPath)

    spark.stop()

  }


}
