package edu.uw.ischool.avajjh.tipcalc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceChargeEditText = findViewById<EditText>(R.id.service_charge)
        val tipButton = findViewById<Button>(R.id.calculate_button)

        serviceChargeEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val amount = s?.toString()?.trim()
                tipButton.isEnabled = amount?.isNotEmpty() == true
//                if (amount?.isNotEmpty() == true) {
//                    val formattedAmount = NumberFormat.getCurrencyInstance(Locale.US).format(amount.toDoubleOrNull() ?: 0.0)
//                    serviceChargeEditText.removeTextChangedListener(this)
//                    serviceChargeEditText.setText(formattedAmount)
//                    serviceChargeEditText.setSelection(formattedAmount.length)
//                    serviceChargeEditText.addTextChangedListener(this)
//                }
            }
        })

        tipButton.setOnClickListener {
            val rawServiceCharge = serviceChargeEditText.text.toString()

            val cleanedServiceCharge = rawServiceCharge.replace("[^\\d.]".toRegex(), "")

            val serviceCharge = cleanedServiceCharge.toDoubleOrNull() ?: 0.0

            val tipAmount = serviceCharge * 0.15  // 15% tip
            val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tipAmount)
            Toast.makeText(this, "Tip Amount: $formattedTip", Toast.LENGTH_LONG).show()
        }
    }
}
