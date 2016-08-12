package com.sjsortablecodingchallenge

import com.sjsortablecodingchallenge.listing.Listing
import com.sjsortablecodingchallenge.product.Product

trait TestFixtures {

  trait Products {
    val sonyCybershot = Product("Sony_Cyber-shot_DSC-W310", "Sony", "DSC-W310", "Cyber-shot", 1262822400000L)
    val sonyCybershotNoDash = Product("Sony_Cyber-shot_DSC-W310", "Sony", "DSC W310", "Cyber-shot", 1262822400000L)
    val samsungTl240 = Product("Samsung_TL240", "Samsung", "TL240", "", 1262736000000L)
  }

  trait Listings {
    val canonPowerShotCAD = Listing("Canon PowerShot D10 12.1 MP Waterproof Digital Camera with 3x Optical Image " +
      "Stabilized Zoom and 2.5-inch LCD (Blue/Silver)", "Canon Canada", "CAD", 420.33)
    val genuineSamsungBattery = Listing("Genuine Samsung EB575152VU i9000 GalaxyS Battery", "Samsung", "CAD", 13.99d)
  }

}
