package com.example.fantasticgoals.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fantasticgoals.R
import com.example.fantasticgoals.databinding.FragmentHomeBinding
import com.example.fantasticgoals.ui.popup.AddGoal
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var goalAdapter: GoalAdapter
    private lateinit var fabAddGoal: FloatingActionButton
    private val goalList = mutableListOf<GoalEntry>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        fabAddGoal = view.findViewById(R.id.fabAddGoal)

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.rvGoalsList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Create an instance of the adapter
        goalAdapter = GoalAdapter()

        // Set the adapter to the RecyclerView
        recyclerView.adapter = goalAdapter



        fabAddGoal.setOnClickListener {
            showAddGoalPopup()
        }



        return view
    }


    private fun showAddGoalPopup() {
        val popup = AddGoal()
        popup.show(parentFragmentManager, "AddGoalPopup")
    }


}