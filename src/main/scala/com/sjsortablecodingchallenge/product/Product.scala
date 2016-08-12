package com.sjsortablecodingchallenge.product

import org.joda.time.DateTime

case class Product(product_name:String, manufacturer:String, model:String, family:String, announcedDate:Long) {
  require(product_name.nonEmpty && manufacturer.nonEmpty && model.nonEmpty)
}