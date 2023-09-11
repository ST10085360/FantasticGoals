package com.example.fantasticgoals

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment

class AddGoalDialogFragment : DialogFragment() {

    interface AddGoalDialogListener {
        fun onAddGoalClicked(title: String, description: String, isComplete: Int)
    }

    private lateinit var listener: AddGoalDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as AddGoalDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement AddGoalDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.add_goal_popup, null)

            val txtGoalTitle = view.findViewById<EditText>(R.id.txtGoalTitle)
            val txtGoalDescription = view.findViewById<EditText>(R.id.txtGoalDescription)
            val spnIsComplete = view.findViewById<Spinner>(R.id.spnIsComplete)
            val btnAddGoal = view.findViewById<Button>(R.id.btnAddGoal)

            btnAddGoal.setOnClickListener {
                val title = txtGoalTitle.text.toString()
                val description = txtGoalDescription.text.toString()
                val isComplete = spnIsComplete.selectedItemPosition

                listener.onAddGoalClicked(title, description, isComplete)
                dismiss()
            }

            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
