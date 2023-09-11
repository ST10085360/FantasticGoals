package com.example.fantasticgoals

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
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

    private lateinit var modeSwitch: SwitchCompat
    private var darkMode: Boolean = false
    private var editor : SharedPreferences.Editor?=null
    private var sharedPref: SharedPreferences?=null


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

        sharedPref = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        darkMode = sharedPref?.getBoolean("night", false)!!

        if(darkMode){
            modeSwitch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        modeSwitch.setOnCheckedChangeListener{compoundButton, state ->
            if(darkMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor = sharedPref?.edit()
                editor?.putBoolean("night", false)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor = sharedPref?.edit()
                editor?.putBoolean("night", true)
            }
            editor?.apply()
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