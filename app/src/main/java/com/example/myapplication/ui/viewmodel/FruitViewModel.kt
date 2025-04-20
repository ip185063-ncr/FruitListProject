package com.example.myapplication.ui.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.beans.PropertyChangeSupport
import com.example.myapplication.data.model.Fruit
import com.example.myapplication.data.repository.FruitRepository
import org.json.JSONObject
import java.beans.PropertyChangeListener
import kotlin.concurrent.thread

class FruitViewModel(val context: Context, private val repository: FruitRepository) {

    private val propertyChangeSupport = PropertyChangeSupport(this)
    var fruits: List<Fruit> = emptyList()
        private set(value) {
            val oldValue = field
            field = value
            propertyChangeSupport.firePropertyChange("fruits", oldValue, value)
        }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchFruits(){
        thread {
            fruits = repository.getFruits(context)
        }
    }

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(listener)
    }

    companion object {
         fun parseFruits(jsonResponse: String): List<Fruit> {
            val jsonObj = JSONObject(jsonResponse)
            val fList = jsonObj.getJSONArray("list")
            val fruitList = mutableListOf<Fruit>()
            val fruitObj = jsonObj.getJSONObject("fruits")
             if (jsonResponse == null || fList == null) {
                return emptyList()
             }
            for (i in 0 until fList.length()) {
                val fruitNameKey = fList.getString(i)
                fruitList.add(Fruit(fruitNameKey,fruitObj.getJSONObject(fruitNameKey).getString("url")))
            }
            Log.i(" Final res :: ", fruitList.toString())
            return fruitList
        }
    }
}