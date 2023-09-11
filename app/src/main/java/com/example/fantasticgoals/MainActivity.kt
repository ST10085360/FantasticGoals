package com.example.fantasticgoals

import android.app.Dialog
import android.content.ContentValues
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fantasticgoals.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AddGoalDialogFragment.AddGoalDialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var addGoalPopup: Dialog
    private lateinit var btnAddGoal: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //for the popup to add a goal
        addGoalPopup = Dialog(this)
        addGoalPopup.setContentView(R.layout.add_goal_popup)
        addGoalPopup.setCancelable(true)
        btnAddGoal = findViewById(R.id.fabAddGoal)

        btnAddGoal.setOnClickListener()
        {
            showAddGoalPopup()
        }



        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onAddGoalClicked(title: String, description: String, isComplete: Int) {
        // Insert the goal into the database using your DbHelper
        val dbHelper = DbHelper(this)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("title", title)
            put("description", description)
            put("is_completed", isComplete)
        }

        db.insert("Goals", null, values)
        db.close()

        // Refresh your UI or do any other necessary tasks after adding the goal
    }

    private fun showAddGoalPopup() {
        val dialog = AddGoalDialogFragment()
        dialog.show(supportFragmentManager, "AddGoalDialogFragment")
    }

}