@file:Suppress("MagicNumber")

package com.jhonprieto.melifinder.ui.screens.productDetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jhonprieto.melifinder.R

fun getSimulatedPrice(): Double = (100..500).random().toDouble()
fun getSimulatedOldPrice(): Double? = if ((0..1).random() == 1) getSimulatedPrice() * 1.15 else null
fun getSimulatedRating(): Float = (3..5).random() + listOf(0.0f, 0.5f).random()
fun getSimulatedReviews(): Int = (1..200).random()

@Composable
fun getSimulatedCondition(condition: String?): String {
    if (!condition.isNullOrBlank()) return condition
    val conditions = listOf(
        stringResource(R.string.new_item),
        stringResource(R.string.used),
        stringResource(R.string.refurbished)
    )
    return conditions.random()
}
