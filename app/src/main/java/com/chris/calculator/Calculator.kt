package com.chris.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel



val buttonList = listOf(
    "C", "(", ")", "/", 
    "7", "8", "9", "*",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "AC", "0", ".", "="

)

@Composable
fun Calculator(modifier: Modifier = Modifier, view: CalculatorView) {

    val equationText = view.equationText.observeAsState()
    val resultText = view.resultText.observeAsState()



    Box(modifier = modifier){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End

    ) {
        Text(text = equationText.value?:"",
            style = TextStyle(
                fontSize = 30.sp,
                textAlign = TextAlign.End
            ),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis


        )

        Spacer(modifier = Modifier.weight(1f))

        Text(text = resultText.value?:"",
            style = TextStyle(
                fontSize = 60.sp,
                textAlign = TextAlign.End
            ),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis


        )
        
        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(4),){

            items(buttonList){
                CalculatorButton(btn = it, onClick = {
                    view.onButtonClick(it)
                })
            }
        }
        
    }
}
}



@Composable
fun CalculatorButton(btn: String,onClick : ()-> Unit ){
    Box(modifier = Modifier.padding(8.dp)){
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            containerColor = getColor(btn)
        )
        {
            Text(text = btn,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                )
        }
    }
}


fun getColor(btn: String) : Color{
    if(btn == "C" || btn =="AC")
        return Color(0xFFFC3535)
    if(btn =="(" || btn == ")" || btn == "=")
        return Color(0xFFC1F56D)
    if(btn == "/" || btn =="*" || btn == "-" || btn == "+")
        return Color(0xFFF5F578)
    return Color(0xFFF79D59)
}