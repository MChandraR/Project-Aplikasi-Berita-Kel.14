package com.mcr.uberita.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcr.uberita.R

class myCustomUI {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun myTextField(
        leadingIcon:  @Composable (() -> Unit)? = null,
        trailingIcon:  @Composable (() -> Unit)? = null,
        supportingText: @Composable (() -> Unit)? = null,
        label: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
        value:String = "",
        modifier: Modifier,
        onValueChange: (String) -> Unit,
        colors:androidx.compose.material3.TextFieldColors = TextFieldDefaults.textFieldColors(colorPalette().invisible),
        shape: Shape = TextFieldDefaults.filledShape
    ){
        val mergedTextStyle = LocalTextStyle.current.merge(androidx.compose.ui.text.TextStyle(color = Color.Black))
        BasicTextField(
            value = value,
            modifier = modifier
                .defaultMinSize(
                    minHeight = 16.dp
                )
                .background(colorPalette().invisible)
                .fillMaxWidth()
                .padding(all = 0.dp),
            onValueChange = onValueChange,
            enabled = true,
            readOnly = false,
            cursorBrush = SolidColor(colorPalette().BlueLight),
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            interactionSource = remember { MutableInteractionSource() },
            singleLine = true,
            maxLines = 1,
            decorationBox = {
                TextFieldDefaults.TextFieldDecorationBox(
                    contentPadding = PaddingValues(0.dp) ,
                    value = value,
                    visualTransformation = VisualTransformation.None,
                    innerTextField = it,
                    placeholder = placeholder,
                    label = label,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    supportingText = supportingText,
                    shape = RoundedCornerShape(50,50,50,50),
                    singleLine = true,
                    enabled = true,
                    isError = false,
                    interactionSource = remember { MutableInteractionSource() },
                    colors = colors
                )
            }

        )
    }

    @Composable
    fun placeHolder(text:String=""){
        Text(text = text, color = colorPalette().gray, fontSize = 14.sp)
    }


    @Composable
    fun myButton(icon: ImageVector, text:String, modifier: Modifier = Modifier){
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .clickable {

                },
        ) {
            Row(horizontalArrangement = Arrangement.Start){
                Image(imageVector = icon , colorFilter = ColorFilter.tint(colorPalette().darkBlue), contentDescription = null)
                Text(modifier = Modifier.padding(start = 5.dp), text = text, color = colorPalette().darkBlue)
            }
        }
    }

    @Composable
    fun beritaListView(judul:String){
        Box(modifier = Modifier.height(120.dp).padding(5.dp).background(Color.Transparent).clip(RoundedCornerShape(10,10,10,10))){
            Row(
                Modifier
                    .background(MaterialTheme.colorScheme.secondary)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp).weight(.6f).fillMaxWidth()) {
                    Text(lineHeight = 14.sp, maxLines = 2, modifier = Modifier.padding(bottom=5.dp),text = judul, color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(lineHeight = 13.sp, maxLines = 3, text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nec leo id lacus imperdiet mattis vitae quis eros. Proin sem purus, blandit sit amet consequat vel, tempor ac sem. Nunc finibus sit amet enim sit amet commodo. Aliquam elit elit, mattis at fringilla porttitor, iaculis in sem. Cras congue velit dolor, quis pretium dolor viverra sed. Cras sapien ligula, condimentum vel leo in, ultricies tempus metus. Vestibulum posuere consectetur dolor a tincidunt. Pellentesque imperdiet volutpat porttitor. Nam ornare massa ultrices aliquet blandit. Nam aliquam sapien nunc, sed dictum risus dictum non. In pulvinar orci sit amet commodo fringilla. Curabitur vel lectus lorem. ", color = MaterialTheme.colorScheme.outline, fontSize = 12.sp)
                }
                Column(modifier = Modifier.weight(.4f).fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.example),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer { alpha = 0.99f }
                            .drawWithContent {
                                val colors = listOf(
                                    Color.Black,
                                    Color.Transparent
                                )
                                drawContent()
                                drawRect(
                                    blendMode = BlendMode.DstOut,
                                    brush = Brush.horizontalGradient(colors),
                                )
                            })
                }
            }
        }

    }

}