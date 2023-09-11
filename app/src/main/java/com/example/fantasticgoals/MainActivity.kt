package com.example.fantasticgoals

import android.content.ContentValues
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fantasticgoals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


    private fun addGoal(goalTitle: String, goalDesc: String, isComplete: Boolean) {
        val dbHelper = DbHelper(this) // 'this' refers to the current context
        val db = dbHelper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("title", goalTitle)
        contentValues.put("description", goalDesc)
        contentValues.put("is_completed", if (isComplete) 1 else 0)

        val result = db.insert("Goals", null, contentValues)
        db.close()

        if (result == -1L) {
            // Insertion failed
            // Handle error if needed
        } else {
            // Insertion successful
            // Handle success if needed
        }
    }


    private fun deleteGoal(goalId: Int) {
        val dbHelper = DbHelper(this) // 'this' refers to the current context
        val db = dbHelper.writableDatabase

        val result = db.delete("Goals", "id=?", arrayOf(goalId.toString()))
        db.close()

        if (result == 0) {
            // Deletion failed, no rows were affected
            // Handle error if needed
        } else {
            // Deletion successful
            // Handle success if needed
        }
    }


}