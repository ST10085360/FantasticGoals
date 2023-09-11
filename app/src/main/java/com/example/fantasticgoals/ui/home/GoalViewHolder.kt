package com.example.fantasticgoals.ui.home

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fantasticgoals.R
class GoalViewHolder(
    itemView: View,
    private val adapter: goalAdapter
) : RecyclerView.ViewHolder(itemView) {
    private val goalTitle: TextView = itemView.findViewById(R.id.txtTitle)
    private val goalDescription: TextView = itemView.findViewById(R.id.txtDescription)

    init {
        itemView.setOnClickListener {
            // Handle item click event here
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val entry = adapter.getGoalEntry(position)
                // Call a function to display detailed information about the selected project
                showProjectDetails(entry)
            }
        }
    }

    private fun showProjectDetails(entry: GoalEntry) {
        val intent = Intent(itemView.context, GoalEntry::class.java)
        intent.putExtra("goalTitle", entry.title)
        intent.putExtra("goalDescription", entry.description)
        itemView.context.startActivity(intent)
    }

    fun bind(entry: GoalEntry) {
        goalTitle.text = entry.title
        goalDescription.text = entry.description
    }
}
