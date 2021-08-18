package com.example.calc_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {

    lateinit var fragmentA:Fragment
    lateinit var fragmentB:Fragment
    lateinit var Add: Button
    lateinit var Sub: Button
    lateinit var Mul: Button
    lateinit var Div: Button
    lateinit var Reset: Button
    lateinit var Action: TextView
    lateinit var Input1: TextView
    lateinit var Input2: TextView
    lateinit var Result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentA = FragmentA()
        fragmentB= FragmentB()

        if(null == savedInstanceState)
            supportFragmentManager.beginTransaction()
            .add(R.id.flayout1, fragmentA,"Add")
            .commit()

    }

    override fun onBackPressed() {

        Add = findViewById(R.id.add)
        Sub = findViewById(R.id.sub)
        Mul = findViewById(R.id.mul)
        Div = findViewById(R.id.div)
        Reset = findViewById(R.id.reset)
        Action = findViewById(R.id.action)
        Input1 = findViewById(R.id.Input1)
        Input2 = findViewById(R.id.Input2)
        Result = findViewById(R.id.result)

        if(Reset.isVisible) {
            Result.isVisible = false
            Input1.isVisible = false
            Input2.isVisible = false
            Action.isVisible = false
            Reset.isVisible = false
            Add.isVisible = true
            Sub.isVisible = true
            Mul.isVisible = true
            Div.isVisible = true
        }
        else
            super.onBackPressed()
    }

}