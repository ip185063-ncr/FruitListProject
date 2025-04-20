package com.example.myapplication.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Fruit
import com.example.myapplication.network.ApiClient
import com.example.myapplication.ui.view.ImageClick


class FruitAdapter (private val fruitList: List<Fruit>): RecyclerView.Adapter<FruitAdapter.FruitViewHolder>(){

    private var mlistener : onItemClickListener?=null
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_fruit, parent, false)
        return FruitViewHolder(view, mlistener)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.name.text = fruit.name
        ApiClient.loadImage(holder.itemView.context, holder.image, fruit.imgURL)

        holder.image.setOnClickListener {
            try {

                val intent = Intent(holder.itemView.context, ImageClick::class.java)
                if(holder.progressBar.getVisibility() == View.VISIBLE){ // check if it is visible
                    holder.progressBar.setVisibility(View.GONE); // if not set it to visible
                    holder.image.setVisibility(View.VISIBLE); // use 1 or 2 as parameters.. arg0 is the view(your button) from the onclick listener
                }
                intent.putExtra("imageUrl", fruit.imgURL)
                intent.putExtra("fruitName", fruit.name)

                startActivity(holder.itemView.context, intent, null)
            } catch (e: Exception) {
                Log.e("FruitAdapter", "Failed to start ImageClickActivity: ${e.message}")

            }
        }

    }

    override fun getItemCount() = fruitList.size

    class FruitViewHolder (view: View, listener: onItemClickListener?) :RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.fruitName)
        val image: ImageButton = view.findViewById(R.id.fruitImage)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(absoluteAdapterPosition)

            }
        }
    }
}