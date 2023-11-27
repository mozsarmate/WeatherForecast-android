package hu.ait.weatherforecast.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CityViewModel : ViewModel(){
    private var _db = mutableStateListOf<City>()

    fun getAllList(): List<City> {
        return _db
    }

    fun addCity(city: City) {
        _db.add(city)
    }

    fun removeCity(city: City) {
        _db.remove(city)
    }
    
}