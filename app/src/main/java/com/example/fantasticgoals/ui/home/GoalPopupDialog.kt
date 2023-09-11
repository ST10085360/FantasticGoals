package com.example.fantasticgoals.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.fantasticgoals.R
//ALL REFERENCES ARE LOCATED IN res/values/referencesOPSC.txt
class GoalPopupDialog : DialogFragment() {
    private var goalTitle: String? = null
    private var goalDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            goalTitle = it.getString(ARG_GOAL_TITLE)
            goalDescription = it.getString(ARG_GOAL_DESCRIPTION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.goal_details_popup, container, false)

        val btnExit = view.findViewById<ImageButton>(R.id.btnExitPopup)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtCategory = view.findViewById<TextView>(R.id.txtDescription)

        txtTitle.text = goalTitle
        txtCategory.text = goalDescription

        btnExit.setOnClickListener {
            dismiss()
        }

        return view
    }

    companion object {
        private const val ARG_GOAL_TITLE = "goal_title"
        private const val ARG_GOAL_DESCRIPTION = "goal_description"

        fun newInstance(goalTitle: String, goalDescription: String): GoalPopupDialog {
            val dialog = GoalPopupDialog()
            val args = Bundle().apply {
                putString(ARG_GOAL_TITLE, goalTitle)
                putString(ARG_GOAL_DESCRIPTION, goalDescription)
            }
            dialog.arguments = args
            return dialog
        }
    }
}