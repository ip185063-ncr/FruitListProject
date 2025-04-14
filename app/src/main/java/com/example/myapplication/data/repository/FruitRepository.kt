package com.example.myapplication.data.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.network.ApiClient
import com.example.myapplication.data.model.Fruit
import java.io.IOException

class FruitRepository(){
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFruits(context: Context): List<Fruit>{
        return ApiClient.fetchFruits(context)
    }

}