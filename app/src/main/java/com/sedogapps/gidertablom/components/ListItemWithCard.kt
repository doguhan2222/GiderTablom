package com.sedogapps.gidertablom.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


    @Composable
    fun ListItemWithCard( isSelected:Boolean,item: String, onItemClick: (String) -> Unit) {
        val interactionSource = remember { MutableInteractionSource() }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected) Color.Black else Color.White,
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                ) { onItemClick(item)}

        ) {
            Text(text = item, modifier = Modifier.padding(12.dp), fontSize = 15.sp, style = TextStyle(color = if (isSelected) Color.White else Color.Black))
        }
    }


