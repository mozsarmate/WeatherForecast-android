import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableDropdown(cities: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    var selectedCity by remember { mutableStateOf<String?>(null) }
    
    val filteredCities = cities.filter {
        it.contains(searchText.text, ignoreCase = true)
    }
    
    Column {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                if(it.text.isEmpty()) expanded = false
                else expanded = true
            },
            placeholder = { Text("Search City") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (searchText.text.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            searchText = TextFieldValue("")
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        
        Box {
            OutlinedTextField(
                value = selectedCity?: "",
                onValueChange = {},
                label = { Text("Selected City") },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                filteredCities.forEach { city ->
                    DropdownMenuItem(
                        onClick = {
                            selectedCity = city
                            expanded = false
                        },
                        text = {
                            Text(text = city)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchableDropdownExample() {
    val cities = listOf(
        "New York",
        "Los Angeles",
        "Chicago",
        "Toronto",
        "London",
        "Paris",
        "Berlin",
        "Moscow",
        "Beijing",
        "Tokyo",
        "Delhi",
        "Mumbai",
        "Sydney",
        "São Paulo",
        "Mexico City",
        "Cairo",
        "Johannesburg",
        "Istanbul",
        "Dubai",
        "Mumbai",
        "Shanghai",
        "Seoul",
        "Bangkok",
        "Singapore",
        "Sydney",
        "Rio de Janeiro",
        "Buenos Aires",
        "Lima",
        "New Delhi",
        "Mumbai",
        "Karachi",
        "Istanbul",
        "Lagos",
        "Cairo",
        "Cape Town",
        "Nairobi",
        "Casablanca",
        "Riyadh",
        "Tel Aviv",
        "Toronto",
        "Vancouver",
        "Montreal",
        "Chicago",
        "Houston",
        "Los Angeles",
        "San Francisco",
        "Miami",
        "Mexico City",
        "Bogotá",
        "Lima",
        "Buenos Aires",
        "Santiago",
        "Rio de Janeiro",
        "São Paulo",
        "London",
        "Manchester",
        "Paris",
        "Marseille",
        "Berlin",
        "Munich",
        "Moscow",
        "Saint Petersburg",
        "Istanbul",
        "Ankara",
        "Dubai",
        "Abu Dhabi",
        "Riyadh",
        "Jeddah",
        "Mecca",
        "Medina",
        "Beijing",
        "Shanghai",
        "Guangzhou",
        "Shenzhen",
        "Seoul",
        "Busan",
        "Tokyo",
        "Osaka",
        "Delhi",
        "Mumbai",
        "Bangalore",
        "Kolkata",
        "Cairo",
        "Alexandria",
        "Johannesburg",
        "Cape Town",
        "Nairobi",
        "Casablanca",
        "Tel Aviv",
        "Jerusalem",
        "Toronto",
        "Vancouver",
        "Montreal",
        "Sydney",
        "Melbourne",
        "Brisbane",
        "Perth",
        "Auckland"
    )
    
    SearchableDropdown(cities = cities)
}