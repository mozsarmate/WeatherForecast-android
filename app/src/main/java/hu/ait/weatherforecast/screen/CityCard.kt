package hu.ait.weatherforecast.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.ait.weatherforecast.data.City

@Composable
fun CityCard(city: City) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = city.name, fontSize = 24.sp, modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "${city.temp} ˙C", fontSize = 24.sp, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(16.dp))
            Column (modifier = Modifier.weight(1f)){
                Text(text = "${city.tempMin} ˙C", fontSize = 18.sp, color = (Color.Blue))
                Text(text = "${city.tempMax} ˙C", fontSize = 18.sp, color = (Color.Red))
            }
            IconButton(onClick = { }, modifier = Modifier.weight(0.5f)) {
                Icon(
                    Icons.Default.Clear, contentDescription = "Delete",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        
    }
    
}