package com.example.fantasticgoals

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


public class GoalAdapter(goals: List<Goal>) :
    RecyclerView.Adapter<GoalAdapter.ViewHolder>() {
    private val goals: List<Goal>

    init {
        this.goals = goals
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.goal_tile, parent, false)
        return ViewHolder(itemView, this)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal: Goal = goals[position]

        // Bind data to the goal tile view
        holder.goalTitleTextView.setText(goal.getTitle())
        // Bind other data as needed
    }

    override fun getItemCount(): Int {
        return goals.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var goalTitleTextView: TextView

        // Declare other UI elements here
        init {
            goalTitleTextView = itemView.findViewById<TextView>(R.id.goalTitle)
            // Initialize other UI elements here
        }
    }
}