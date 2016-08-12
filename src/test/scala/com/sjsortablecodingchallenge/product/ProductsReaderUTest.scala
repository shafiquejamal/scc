package com.sjsortablecodingchallenge.product

import org.apache.spark.sql.SparkSession
import org.scalatest.{FlatSpec, ShouldMatchers}

class ProductsReaderUTest extends FlatSpec with ShouldMatchers {

  "the reader" should "convert dataframe objects to datasets with type product" in {

    implicit val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()

    val products =
      ProductsReader.read(spark.read.json("src/test/scala/com/sjsortablecodingchallenge/product/TestProducts.txt"))
    val expected =
      Seq(
        Product("Sony_Cyber-shot_DSC-W310", "Sony", "DSC-W310", "Cyber-shot", 1262822400000L),
        Product("Samsung_TL240", "Samsung", "TL240", "", 1262736000000L)
      )

    products.collect() should contain theSameElementsAs expected

    spark.stop()

  }

}
