package com.fired.rate.model

import android.content.LocusId

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */

data class Rate(
    val symbol: String,
    val rate: Int
)

data class Rates(
    val rates: List<Rate>
)