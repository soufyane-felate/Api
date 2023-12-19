package com.example.api_trantinet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TrantinetAdapter(private val context: Context, private val trantinets: List<Trantinet>) :
    RecyclerView.Adapter<TrantinetAdapter.TrantinetViewHolder>() {

    inner class TrantinetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
        val batteryLevelTextView: TextView = itemView.findViewById(R.id.tv_pattery)
        val maxDistanceTextView: TextView = itemView.findViewById(R.id.tv_max)
        val imageView: ImageView = itemView.findViewById(R.id.tv_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrantinetViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false)
        return TrantinetViewHolder(view)
    }


    override fun onBindViewHolder(holder: TrantinetViewHolder, position: Int) {
        val currentItem = trantinets.getOrNull(position)

        currentItem?.let {
            holder.nameTextView.text = it.name
            holder.batteryLevelTextView.text = "${it.battery_level}%"
            holder.maxDistanceTextView.text = "${it.max_distance} km"

            Picasso.get()
                .load(it.image)
                // .resize(150, 140)
                //  .centerCrop()
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return trantinets.size
    }
}


