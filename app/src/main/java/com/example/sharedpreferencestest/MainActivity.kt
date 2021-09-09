package com.example.sharedpreferencestest

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputId = findViewById<EditText>(R.id.editId)
        val inputName = findViewById<EditText>(R.id.editName)
        val outputId = findViewById<TextView>(R.id.textViewShowId)
        val outputName = findViewById<TextView>(R.id.textViewShowName)

        val btnSave = findViewById<Button>(R.id.save)
        val btnView = findViewById<Button>(R.id.view)
        val btnClear = findViewById<Button>(R.id.clear)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        saveButton(btnSave, inputId, inputName, sharedPreferences)
        viewButton(btnView, sharedPreferences, outputName, outputId)
        clearButton(btnClear, sharedPreferences, outputName, outputId)
    }

    private fun clearButton(
        btnClear: Button,
        sharedPreferences: SharedPreferences,
        outputName: TextView,
        outputId: TextView
    ) {
        btnClear.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            outputName.setText("").toString()
            outputId.text = "".toString()
        })
    }

    @SuppressLint("SetTextI18n")
    private fun viewButton(
        btnView: Button,
        sharedPreferences: SharedPreferences,
        outputName: TextView,
        outputId: TextView
    ) {
        btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
            if (sharedIdValue == 0 && sharedNameValue.equals("defaultname")) {
                outputName.setText("default name: $sharedNameValue").toString()
                outputId.text = "default id: ${sharedIdValue.toString()}"
            } else {
                outputName.setText(sharedNameValue).toString()
                outputId.text = sharedIdValue.toString()
            }

        }
    }

    private fun saveButton(
        btnSave: Button,
        inputId: EditText,
        inputName: EditText,
        sharedPreferences: SharedPreferences
    ) {
        btnSave.setOnClickListener(View.OnClickListener {
            val id: Int = Integer.parseInt(inputId.text.toString())
            val name: String = inputName.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
    //            editor.commit()
        })
    }
}