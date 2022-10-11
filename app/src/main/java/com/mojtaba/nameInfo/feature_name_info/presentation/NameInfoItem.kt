package com.mojtaba.nameInfo.feature_name_info.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameDetails
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun NameInfoItem(nameInfo :NameDetails,modifier: Modifier = Modifier){
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING
    val probability = df.format(nameInfo.probability).toDouble()
    Column(
        modifier = modifier
    ) {
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                    )
            ){
                append("Country: ")
            }

            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp
                )
            ){
                append(nameInfo.countryId)
            }
        })
        Divider()
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            ){
                append("Probability: ")
            }

            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp
                )
            ){

                append("$probability %")
            }
        })
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth(),progress = probability.toFloat())

    }
}