package com.sjsortablecodingchallenge.matching

import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.product.Product
import org.apache.commons.lang3.StringUtils.containsIgnoreCase

object MatchingAlgorithms {

  val matchUsingRegexsDashesSpaces: (Product, Listing) => Boolean = (product, listing) => {
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
    !containsIgnoreCase(listing.title, " for ") &&
    !containsIgnoreCase(listing.title, " f\u00FCr ") &&
    !containsIgnoreCase(listing.title, " f?r ")
  }

}
