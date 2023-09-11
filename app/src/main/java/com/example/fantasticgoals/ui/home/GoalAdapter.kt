package com.example.fantasticgoals.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fantasticgoals.R

class GoalAdapter : ListAdapter<GoalEntry, GoalAdapter.goalViewHolder>(
    GoalDiffCallback()
) {

    //ALL REFERENCES ARE LOCATED IN res/values/referencesOPSC.txt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): goalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.goal_tile, parent, false)
        return goalViewHolder(itemView, this)
    }

    fun getGoalEntry(position: Int): GoalEntry {
        return getItem(position)
    }


    override fun onBindViewHolder(holder: goalViewHolder, position: Int) {
        val goalEntry = getItem(position)

        holder.goalTitle.text = goalEntry.title
        holder.goalDescription.text = goalEntry.description



        holder.itemView.findViewById<Button>(R.id.btnGoalAction).setOnClickListener {
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val dialog = GoalPopupDialog.newInstance(
                goalEntry.title,
                goalEntry.description
            )
            dialog.show(fragmentManager, "goalPopupDialog")
        }
    }


    inner class goalViewHolder(itemView: View, goalAdapter: GoalAdapter) : RecyclerView.ViewHolder(itemView) {
        val goalTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val goalDescription: TextView = itemView.findViewById(R.id.txtDescription)
    }

    class GoalDiffCallback : DiffUtil.ItemCallback<GoalEntry>() {
        override fun areItemsTheSame(oldItem: GoalEntry, newItem: GoalEntry): Boolean {
            return oldItem.title == newItem.title
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: GoalEntry, newItem: GoalEntry): Boolean {
            return oldItem.title == newItem.title
            return oldItem.description == newItem.description
        }
    }
}