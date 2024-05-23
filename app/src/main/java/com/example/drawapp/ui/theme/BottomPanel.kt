package com.example.drawapp.ui.theme

import android.graphics.drawable.Icon
import android.provider.SyncStateContract.Columns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drawapp.R

@Composable
fun BottomPanel(
    onClick: (Color) -> Unit,
    onLineWidthChange: (Float) -> Unit,
    onBackClick:() -> Unit,
    onCapClick:(StrokeCap) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorList{color ->
            onClick(color)
        }
        CustomSlider{ lineWidth ->
            onLineWidthChange(lineWidth)
        }
        ButtonPanel({
            onBackClick()
        }) {cap ->
            onCapClick(cap)
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
fun ColorList(onClick: (Color) -> Unit ){
    val colors = listOf(
        Color.Blue,
        Color.Red,
        Color.Black,
        Color.Magenta,
        Color.Yellow,
        Color.Green,
    )

    LazyRow(modifier = Modifier.padding(10.dp)
    ){
        items(colors){color ->
            Box(
                modifier = Modifier
                    //отступ только справа
                    .padding(end = 5.dp)
                    .clickable {
                        onClick(color)
                    }
                    .size(40.dp) //круглой формы
                    .background(color, CircleShape)
            )
        }
    }
}

@Composable
fun CustomSlider(onChange: (Float) -> Unit){
    var position by remember {
        mutableStateOf(0.05f)
    }
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Line width: ${(position * 100).toInt()}")
        Slider(
            value = position,
            onValueChange = {
                val tempPos = if (it > 0) it else 0.01f
                position = tempPos
                onChange(tempPos * 100)
            }
        )

    }
}

//кнопка отмены последнего действия
@Composable
fun ButtonPanel (onClick: () -> Unit, onCapClick: (StrokeCap) -> Unit){
    Row(Modifier.fillMaxWidth()) {
        Row (
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick ={
                    onClick()
                }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }



        //еще одна кнопка
        Row (
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            IconButton(
                //белая кргулая
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick ={
                    onCapClick(StrokeCap.Round)
                })
            {
                Icon(
                   painter = painterResource(id = R.drawable.cap_1),
                    contentDescription = null
                )
            }



            //2 кнопка
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick ={
                    onCapClick(StrokeCap.Square)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.cap_2),
                    contentDescription = null
                )
            }

            //3 кнопка
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick ={
                    onCapClick(StrokeCap.Butt)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.cap_3),
                    contentDescription = null
                )
            }
        }
    }
}