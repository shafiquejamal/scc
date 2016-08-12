package com.sjsortablecodingchallenge.product

import com.sjsortablecodingchallenge.TestFixtures
import org.apache.spark.sql.SparkSession
import org.scalatest.{FlatSpec, ShouldMatchers}

class ProductsReaderUTest extends FlatSpec with ShouldMatchers with TestFixtures {

  "the reader" should "convert dataframe objects to datasets with type product" in new Products {

    implicit val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()

    val products =
      ProductsReader.read(spark.read.json("src/test/scala/com/sjsortablecodingchallenge/product/TestProducts.txt"))
    val expected = Seq(sonyCybershot, samsungTl240)

    products.collect() should contain theSameElementsAs expected

    spark.stop()

  }

}
