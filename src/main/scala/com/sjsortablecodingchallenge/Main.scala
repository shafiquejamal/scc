package com.sjsortablecodingchallenge

import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]): Unit = {

    implicit val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()

    val productsPath = args(0)
    val listingsPath = args(1)
    val outputPath = args(2)

    spark.stop()

  }


}
