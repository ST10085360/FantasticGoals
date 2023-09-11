package com.example.fantasticgoals.ui.popup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.fantasticgoals.R
import com.google.android.material.button.MaterialButton

//ALL REFERENCES ARE LOCATED IN res/values/referencesOPSC.txt
class AddGoal : DialogFragment() {


    private lateinit var btnSave: MaterialButton
    private lateinit var btnCancel: ImageButton
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var context: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.goal_add, container, false)
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)



        btnSave = view.findViewById(R.id.btnSave)
        btnCancel = view.findViewById(R.id.btnExit)
        etTitle = view.findViewById(R.id.txtTitle)
        etDescription = view.findViewById(R.id.txtDescription)

        // Initialize other fields
        btnSave.setOnClickListener {

            val title = etTitle.text.toString()
            val description = etDescription.text.toString()

            dismiss()
        }


        btnCancel.setOnClickListener {
            dismiss()
        }

        return view
    }

}

