package com.sjsortablecodingchallenge

import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]): Unit = {

    implicit val spark = SparkSession.builder().appName("codingChallenge").master("local").getOrCreate()


    spark.stop()

  }


}
