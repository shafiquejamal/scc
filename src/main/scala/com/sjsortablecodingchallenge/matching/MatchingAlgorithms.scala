package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.product.Product
import org.apache.commons.lang3.StringUtils.containsIgnoreCase

object MatchingAlgorithms {

  val matchManufAndRestTitle: (Product, Listing) => Boolean = (product, listing) => {
    containsIgnoreCase(listing.manufacturer, product.manufacturer) &&
    containsIgnoreCase(listing.title, product.model) &&
    containsIgnoreCase(listing.title, product.family)
  }

  val matchManufAndModelInTitle: (Product, Listing) => Boolean = (product, listing) => {
    containsIgnoreCase(listing.manufacturer, product.manufacturer) &&
    containsIgnoreCase(listing.title, product.model)
  }

  val matchManufAndModelInTitleNoForInTitle: (Product, Listing) => Boolean = (product, listing) => {
    containsIgnoreCase(listing.manufacturer, product.manufacturer) &&
    containsIgnoreCase(listing.title, product.model) &&
    !containsIgnoreCase(listing.title, " for ")
  }

  val matchUsingRegexs: (Product, Listing) => Boolean = (product, listing) => {
    val patternManufacturer = ("""(?i)\b""" + product.manufacturer + """\b""").r
    val patternModel = ("""(?i)\b""" + product.model + """\b""").r

    patternManufacturer.findFirstIn(listing.manufacturer).nonEmpty &&
    patternModel.findFirstIn(listing.title).nonEmpty &&
    !containsIgnoreCase(listing.title, " for ")
  }

  val matchUsingRegexsDealWithDashesSpaces: (Product, Listing) => Boolean = (product, listing) => {
    val patternManufacturer = ("""(?i)\b""" + product.manufacturer + """\b""").r
    val patternModel = ("""(?i)\b""" + product.model + """\b""").r
    val patternModelNoSpaces = ("""(?i)\b""" + product.model.replaceAll("""\s""", "") + """\b""").r
    val patternModelDashIntoSpace = ("""(?i)\b""" + product.model.replaceAll("""-""", " ") + """\b""").r
    val patternModelRemoveDash = ("""(?i)\b""" + product.model.replaceAll("""-""", "") + """\b""").r
    val patternModelSpaceToDash = ("""(?i)\b""" + product.model.replaceAll(""" """, "-") + """\b""").r

    patternManufacturer.findFirstIn(listing.manufacturer).nonEmpty &&
    (patternModel.findFirstIn(listing.title).nonEmpty ||
     patternModelNoSpaces.findFirstIn(listing.title).nonEmpty ||
     patternModelDashIntoSpace.findFirstIn(listing.title).nonEmpty ||
     patternModelRemoveDash.findFirstIn(listing.title).nonEmpty ||
     patternModelSpaceToDash.findFirstIn(listing.title).nonEmpty) &&
    !containsIgnoreCase(listing.title, " for ")
  }
  
}
