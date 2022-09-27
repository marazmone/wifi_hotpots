package com.marazmone.mapsclustertest.presentation.screen.googlemap.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marazmone.mapsclustertest.domain.model.AppMarkerSwitch

@Composable
fun AppMarkerSwitchItem(
    item: AppMarkerSwitch,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 16.dp)
    ) {
        Image(
            painter = painterResource(id = item.drawableRes),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Switch(
            checked = item.enabled,
            onCheckedChange = {
                item.enabled = item.enabled.not()
                onClick.invoke()
            },
        )
    }
}