package com.sjsortablecodingchallenge.product

import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, SparkSession}
import org.joda.time.format.DateTimeFormat

object ProductsReader {

  def read(productsPath: String)(implicit spark: SparkSession): Dataset[Product] = {
    import spark.implicits._
    val df = spark.read.json(productsPath)
    df
    .withColumn("announcedDate", toJodaTime(df("announced-date"))).drop("announced-date")
    .withColumn("family", toEmptyString(df("family")))
    .as[Product]
  }

  val dateTimeFormatter = DateTimeFormat.forPattern("yyy-MM-dd'T'k:m:s.SSSZ")

  def toJodaTime = udf[Long, String](dateTimeFormatter.parseDateTime(_).getMillis)

  def toEmptyString = udf[String, Any](Option(_).map(_.toString).getOrElse(""))

}
