package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operator: String = ""
    private var result: Double = 0.0
    private var isNewOperation: Boolean = true
    private var displayString: String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        //Number Buttons
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)

        //Operator Buttons
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val clearButton: Button = findViewById(R.id.clearButton)

        //Number Button Click Listeners
        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }

        //Operator Button Click Listeners
        buttonAdd.setOnClickListener { setOperator("+") }
        buttonSubtract.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonDivide.setOnClickListener { setOperator("/") }
        buttonEquals.setOnClickListener { calculate() }
        clearButton.setOnClickListener { clear() }


        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }*/ }

    private fun appendNumber(number: String) {
        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        }else {
            resultTextView.append(number)
        }
    }

    private fun setOperator(op: String) {
        if (resultTextView.text.isNotEmpty()){
            operand1 = resultTextView.text.toString().toDouble()
            operator = op
            displayString = "$operand1 $operator "
            resultTextView.text = displayString
            isNewOperation = true
        }
    }

    private fun calculate() {
        if (resultTextView.text.isNotEmpty()) {
            operand2 = resultTextView.text.toString().toDouble()
            when (operator) {
                "+" -> result = operand1 + operand2
                "-" -> result = operand1 - operand2
                "*" -> result = operand1 * operand2
                "/" -> {
                    if (operand2 != 0.0) {
                        result = operand1 / operand2
                    } else {
                        resultTextView.text = "Error"
                        return
                    }
                }
            }
            resultTextView.text = result.toString()
            isNewOperation = true
            operand1 = result
            operator = ""
            }
        }

    private fun clear() {
        resultTextView.text = "0"
        operand1 = 0.0
        operand2 = 0.0
        operator = ""
        result = 0.0
        isNewOperation = true
    }
}

