package com.sjsortablecodingchallenge

import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.matching.ProductAndMatchingListings
import com.sjsortablecodingchallenge.product.Product

trait TestFixtures {

  trait Products {
    val sonyCybershot = Product("Sony_Cyber-shot_DSC-W310", "Sony", "DSC-W310", "Cyber-shot", 1262822400000L)
    val sonyCybershotNoDash = Product("Sony_Cyber-shot_DSC-W310", "Sony", "DSC W310", "Cyber-shot", 1262822400000L)
    val samsungTl240 = Product("Samsung_TL240", "Samsung", "TL240", "", 1262736000000L)
  }

  trait Listings {
    val canonPowerShotCAD = Listing("Canon PowerShot D10 12.1 MP Waterproof Digital Camera with 3x Optical Image " +
      "Stabilized Zoom and 2.5-inch LCD (Blue/Silver)", "Canon Canada", "CAD", "420.33")
    val genuineSamsungBattery = Listing("Genuine Samsung EB575152VU i9000 GalaxyS Battery", "Samsung", "CAD", "13.99")
    val sonyCybershotListing1 = Listing("Sony Cybershot DSC-W310", "Sony", "USD", "100.00")
    val sonyCybershotListing2 = Listing("New Cybershot DSC-W310 Sony", "Sony", "CAD", "130.11")
  }

  trait Matches extends Listings with Products {
    val productAndMatchingListings1 =
      ProductAndMatchingListings(sonyCybershot.product_name, Seq(sonyCybershotListing1, sonyCybershotListing2))
    val productAndMatchingListings2 =
      ProductAndMatchingListings(samsungTl240.product_name, Seq(genuineSamsungBattery))

    val expectedJsonAsString =
      """[{"product_name":"Sony_Cyber-shot_DSC-W310","listings":[{"title":"Sony Cybershot DSC-W310","manufacturer":""" +
      """"Sony","currency":"USD","price":"100.00"},{"title":"New Cybershot DSC-W310 Sony","manufacturer":"Sony",""" +
      """"currency":"CAD","price":"130.11"}]},{"product_name":"Samsung_TL240","listings":[{"title":"Genuine Samsung """ +
      """EB575152VU i9000 GalaxyS Battery","manufacturer":"Samsung","currency":"CAD","price":"13.99"}]}]"""
  }

}
