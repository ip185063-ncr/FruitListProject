package com.example.myapplication.network

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import coil.ImageLoader
import coil.disk.DiskCache
import coil.load
import coil.memory.MemoryCache
import com.example.myapplication.data.model.Fruit
import com.example.myapplication.ui.viewmodel.FruitViewModel
import java.net.HttpURLConnection
import java.net.URL
import java.io.IOException


object ApiClient {
    private var API_URL = "http://httpbin.org/json"
    private var fileName = "payload.json"
    @RequiresApi(Build.VERSION_CODES.O)

    fun fetchFruits(context: Context): List<Fruit> {
        val url = URL(API_URL)
        var connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.instanceFollowRedirects = false
        if (connection.responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
            connection = url.openConnection() as HttpURLConnection
            connection.instanceFollowRedirects = true
            Log.i("Error", "ConnectionState ${connection.responseCode}")
        }
        Log.i("ConnectionState", connection.responseMessage)
        Log.i("ConnectionState", "ConnectionState ${connection.responseCode}")
        Log.i("ConnectionState", connection.toString())
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            // val fileInString = connection.inputStream.bufferedReader().use { it.readText() }
            val fileInString = readJsonFromAssets(fileName,context)
            if(fileInString.isEmpty()) return emptyList();
            Log.i( "List: ", FruitViewModel.parseFruits(fileInString).toString())
            return FruitViewModel.parseFruits(fileInString)

        } else {
            Log.i("Connection Failed", "Loading default file. To change, go to ApiClient.kt and change fileName variable")
            val fileInString = readJsonFromAssets(fileName,context)
            Log.i( "List: ", FruitViewModel.parseFruits(fileInString).toString())
            return FruitViewModel.parseFruits(fileInString)
        }
    }

    fun loadImage(context: Context, imageView: ImageButton, url: String){

        val imageLoader = ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
        imageView.load(url, imageLoader)
    }
}
    fun readJsonFromAssets(fileName: String, context: Context): String {
        return try {

            context.assets.open(fileName).bufferedReader().use { it.readText() }
        }
        catch (e : IOException){
            e.printStackTrace()
            ""
        }
    }





