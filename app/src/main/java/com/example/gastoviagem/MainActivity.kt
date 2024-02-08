package com.example.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.buttonCalcular?.setOnClickListener(this)
    }

    private fun captureValues() {
        var distancia = binding?.etDistance?.text.toString()
        var preco = binding?.etPreco?.text.toString()
        var autonomia = binding?.etAutonomia?.text.toString()

        if (distancia.isNotEmpty() && preco.isNotEmpty() && autonomia.isNotEmpty()) {
            try {
                val distance = distancia.toFloat()
                val price = preco.toFloat()
                val autonomy = autonomia.toFloat()
                calculateTotal(distance, price, autonomy)
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.format_invalid),
                    Toast.LENGTH_LONG
                ).show()
                binding?.tvTotal?.text = "R$ 0.00"
            }
        } else {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.complete_required_field),
                Toast.LENGTH_LONG
            ).show()
            binding?.tvTotal?.text = "R$ 0.00"
        }
    }

    private fun calculateTotal(distance: Float, price: Float, autonomia: Float) {

        var total = (distance * price) / autonomia
        val totalFormated = String.format("%.2f", total)
        binding?.tvTotal?.text = "R$ ${totalFormated}"
    }

    override fun onClick(p0: View?) {
        captureValues()
    }
}