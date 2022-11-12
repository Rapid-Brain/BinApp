package com.fired.core2.base

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author yaya (@yahyalmh)
 * @since 12th November 2022
 */

@Preview(showSystemUi = true, name = "phone", device = Devices.PHONE)
@Preview(showSystemUi = true, name = "foldable", device = Devices.FOLDABLE)
@Preview(showSystemUi = true,name = "custom", device = "spec:width=1280dp, height=800dp,dpi=480")
@Preview(showSystemUi = true,name = "tablet", device = Devices.TABLET)
@Preview(showSystemUi = true,name = "desktop", device = "id:desktop_medium")
annotation class ReferenceDevices


@Preview(name = "wear_large_round", showSystemUi = true, device = Devices.WEAR_OS_LARGE_ROUND)
@Preview(name = "wear_small_round", showSystemUi = true, device = Devices.WEAR_OS_SMALL_ROUND)
@Preview(name = "wear_rect", showSystemUi = true, device = Devices.WEAR_OS_RECT)
@Preview(name = "wear_square", showSystemUi = true, device = Devices.WEAR_OS_SQUARE)
annotation class WearPreviewDevices