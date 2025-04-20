package com.example.myapplication.ui.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R
import com.example.myapplication.data.repository.FruitRepository
import com.example.myapplication.ui.adapter.FruitAdapter
import com.example.myapplication.ui.viewmodel.FruitViewModel
import java.beans.PropertyChangeListener


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: FruitViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


     @RequiresApi(Build.VERSION_CODES.O)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         setContentView(R.layout.activity_main)
         viewModel = FruitViewModel(getApplicationContext(), FruitRepository())
         recyclerView = findViewById(R.id.recyclerView)
         swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout)
         recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
         val adapter =FruitAdapter(viewModel.fruits)

         viewModel.fetchFruits()
         val fruitChangeListener = PropertyChangeListener{
             if(it.propertyName == "fruits"){
                 runOnUiThread{
                     recyclerView.adapter = FruitAdapter(viewModel.fruits)
                 }
             }
         }

         viewModel.addPropertyChangeListener(fruitChangeListener)
         swipeRefreshLayout.setOnRefreshListener{
             swipeRefreshLayout.isRefreshing = true
             viewModel.fetchFruits()
             swipeRefreshLayout.isRefreshing = false
         }
         recyclerView.adapter = adapter
    }
}

