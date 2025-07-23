@file:Suppress("MagicNumber")

package com.jhonprieto.melifinder.ui.screens.productDetail

fun getSimulatedPrice(): Double = (100..500).random().toDouble()
fun getSimulatedOldPrice(): Double? = if ((0..1).random() == 1) getSimulatedPrice() * 1.15 else null
fun getSimulatedRating(): Float = (3..5).random() + listOf(0.0f, 0.5f).random()
fun getSimulatedReviews(): Int = (1..200).random()
