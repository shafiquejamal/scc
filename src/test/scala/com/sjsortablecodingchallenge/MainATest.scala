package com.sjsortablecodingchallenge

import java.io.File._

import org.scalatest.{FlatSpec, ShouldMatchers}

class MainATest extends FlatSpec with ShouldMatchers {

  "The application" should "match the listings to the products and save the results to the given file" in {

    val tempOutputFile = createTempFile("output.txt", ".txt")

    val args =
      Array(
        "",
        "src/test/scala/com/sjsortablecodingchallenge/ATestProducts.txt",
        "src/test/scala/com/sjsortablecodingchallenge/ATestListings.txt",
        tempOutputFile.getAbsolutePath)

    Main.main(args)

    scala.io.Source.fromFile(tempOutputFile).mkString shouldEqual
      """{"product_name":"Fujifilm_FinePix_A170","listings":[{"title":"Fujifilm FinePix A170 Digital Camera - Silver """ +
      """(10MP, 3x Zoom) 2.7 inch LCD","manufacturer":"FUJIFILM","currency":"GBP","price":"39.99"},{"title":"Fujifilm """ +
      """FinePix A170 Digital Camera - Silver (10MP, 3x Zoom) 2.7 inch LCD","manufacturer":"FUJIFILM","currency":"GBP",""" +
      """"price":"35.99"}]}""" + "\n" +
      """{"product_name":"Samsung_TL240","listings":[{"title":"Samsung TL240 - Digital camera - """ +
      """compact - 14.2 Mpix - optical zoom: 7 x - supported memory: microSD, microSDHC - black","manufacturer":""" +
      """"Samsung","currency":"USD","price":"210.00"},{"title":"Samsung TL240 - Digital camera - compact - 14.2 Mpix """ +
      """- optical zoom: 7 x - supported memory: microSD, microSDHC - black","manufacturer":"Samsung","currency":"USD",""" +
      """"price":"265.99"}]}"""

    tempOutputFile.delete()

  }

}
