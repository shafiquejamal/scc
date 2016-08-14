package com.sjsortablecodingchallenge.product

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.joda.time.format.DateTimeFormat

object ProductsReader {

  def read(productsPath: String)(implicit spark: SparkSession): Seq[Product] = {
    import spark.implicits._
    val df = spark.read.json(productsPath)
    df
    .withColumn("announcedDate", toJodaTime(df("announced-date"))).drop("announced-date")
    .withColumn("family", toEmptyString(df("family")))
    .as[Product].collect().toSeq
  }

  val dateTimeFormatter = DateTimeFormat.forPattern("yyy-MM-dd'T'k:m:s.SSSZ")

  def toJodaTime = udf[Long, String](dateTimeFormatter.parseDateTime(_).getMillis)

  def toEmptyString = udf[String, Any](Option(_).map(_.toString).getOrElse(""))

}
