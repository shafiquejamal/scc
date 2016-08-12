package com.sjsortablecodingchallenge.listing

import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object ListingsReader {

  def read(df:DataFrame)(implicit spark: SparkSession):Dataset[Listing] = {
    import spark.implicits._
    df.withColumn("price", toBigDecimal(df("price"))).as[Listing]
  }

  private def toBigDecimal = udf[BigDecimal, String](BigDecimal(_))
}
