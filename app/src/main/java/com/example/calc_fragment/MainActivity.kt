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
        if(null == savedInstanceState)
            supportFragmentManager.beginTransaction()
            .add(R.id.flayout1, fragmentA,"fA")
            .commit()

    }
}