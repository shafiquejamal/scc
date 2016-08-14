package com.sjsortablecodingchallenge.listing

import org.apache.spark.sql.{Dataset, SparkSession}

object ListingsReader {

  def read(listingsPath: String)(implicit spark: SparkSession): Seq[Listing] = {
    import spark.implicits._
    spark.read.json(listingsPath).as[Listing].collect().toSeq
  }

}
