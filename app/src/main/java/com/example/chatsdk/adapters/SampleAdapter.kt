package com.example.chatsdk.adapters

/**
 * Created by Aneesh NN on 6/9/21.
 */

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.chatsdk.R
import com.example.chatsdk.interfaces.onItemClickListener

class SampleAdapter(
    private val context: Context,
    val items: ArrayList<String>,
    var itemClickListener: onItemClickListener
) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.row_dummy_users, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = items[position]
        holder.textViewUserName.text = userData.toString()
        holder.containerParent.setOnClickListener {
            itemClickListener.onClick(position, userData)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewUserName: TextView = view.findViewById(R.id.text_view_user_name)
        val containerParent: ConstraintLayout = view.findViewById(R.id.container_parent)
    }
}