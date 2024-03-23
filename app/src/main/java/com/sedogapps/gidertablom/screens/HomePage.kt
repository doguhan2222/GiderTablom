package com.sedogapps.gidertablom.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sedogapps.gidertablom.R
import com.sedogapps.gidertablom.components.CircularImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun HomePage() {


    Scaffold(
        topBar = {
            null
        }
    ) {it
        Spacer(modifier = Modifier.size(10.dp))

        Column {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(top = 10.dp, start = 10.dp) // İstenirse kenar boşluk eklenebilir

            )
            {
                CircularImage(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Hey,its me.",
                    imageSize = 120,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "Good morning, Jon",
                        style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Track your expenses , start your day right",
                        style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }

    }
}

