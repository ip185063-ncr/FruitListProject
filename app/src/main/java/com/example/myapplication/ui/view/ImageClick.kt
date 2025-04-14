package com.example.myapplication.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.myapplication.R
import com.example.myapplication.ui.viewmodel.FruitViewModel


class ImageClick : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_button_view)

        val screenFruit : TextView = findViewById(R.id.screenFruit)
        val screenImage : ImageView = findViewById(R.id.screenImg)
        val backButton : Button = findViewById(R.id.backButton)
        val progressBar : ProgressBar = findViewById(R.id.progressBar)

        val bundle : Bundle?= intent.extras
        val fruit = bundle?.getString("fruitName")
        val img = bundle?.getString("imageUrl")


        screenFruit.text = fruit
        if (img != null) {
            loadImage(screenImage, img)
        }

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            if(progressBar.getVisibility() != View.INVISIBLE){ // check if it is visible
                progressBar.setVisibility(View.INVISIBLE); // if not set it to visible
                backButton.setVisibility(View.VISIBLE); // use 1 or 2 as parameters.. arg0 is the view(your button) from the onclick listener
            }
            startActivity(intent)
            finish()
        }
    }

    private fun loadImage(imageView: ImageView, url: String){
        imageView.load(url)
    }


}