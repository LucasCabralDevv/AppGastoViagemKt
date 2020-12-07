package com.lucascabral.gastodeviagem

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculateButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        val id = view.id
        if (id == R.id.calculateButton) {
            calculate()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {

        if (calculateValidation()) {

            try {
                val distance = distanceEditText.text.toString().toFloat()
                val price = priceEditText.text.toString().toFloat()
                val autonomy = autonomyEditText.text.toString().toFloat()

                val calculateValue = (distance * price) / autonomy
                resultTextView.text = "R$ ${"%.2f".format(calculateValue)}"
            } catch (nfe: NumberFormatException) {
                Toast.makeText(
                    this,
                    getString(R.string.main_nfException_toast),
                    Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(
                this,
                getString(R.string.main_validation_toast),
                Toast.LENGTH_LONG).show()
        }
    }

    private fun calculateValidation(): Boolean {
        return (distanceEditText.text.toString().isNotEmpty()
                && priceEditText.text.toString().isNotEmpty()
                && autonomyEditText.text.toString().isNotEmpty()
                && autonomyEditText.text.toString() != "0")
    }
}