package hu.ait.weatherforecast

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import hu.ait.weatherforecast.data.City
import hu.ait.weatherforecast.data.CityViewModel
import hu.ait.weatherforecast.screen.CityCard
import hu.ait.weatherforecast.ui.theme.WeatherForecastTheme


class MainActivity : ComponentActivity() {
    private val cityViewModel by lazy { CityViewModel() }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherForecastApp(viewModel = cityViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherForecastApp(
    modifier: Modifier = Modifier,
    viewModel: CityViewModel
) {
    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Weather Forecast") },
                actions = {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                modifier = Modifier.padding(16.dp)
            ) { Text(text = "Add City", modifier = Modifier.padding(16.dp)) }
        },
        content = { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp)
            ) {
                if (viewModel.getAllList().isEmpty()) {
                    Text(text = "No Cities Added")
                } else {
                    Log.d("MAIN", viewModel.getAllList().toString())
                    LazyColumn() {
                        items(viewModel.getAllList()) { cur ->
                            CityCard(cur)
                        }
                    }
                }
            }
            
        }
    )
    
    if (showDialog) {
        AddCityDialog(
            onDismissRequest = { showDialog = false },
            viewModel = viewModel
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityDialog(
    onDismissRequest: () -> Unit,
    viewModel: CityViewModel,
    modifier: Modifier = Modifier
) {
    
    var cityName by rememberSaveable {
        mutableStateOf("")
    }
    
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
        ) {
            
            Text(text = "Add New City", modifier.padding(8.dp))
            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it },
                modifier
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    .padding(8.dp),
                label = { Text(text = "City Name") }
            )
            //SearchableDropdownExample()
            
            Button(onClick = {
                viewModel.addCity(City(name = cityName))
                Log.d("MAIN", "added")
                onDismissRequest()
            }, modifier.padding(8.dp)) {
                Text(text = "Add City")
            }
        }
    }
    
}
