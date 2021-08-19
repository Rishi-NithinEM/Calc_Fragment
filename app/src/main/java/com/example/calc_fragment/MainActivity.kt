package com.example.calc_fragment

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {

    lateinit var fragmentA: Fragment
    lateinit var fragmentB: Fragment
    lateinit var Add: Button
    lateinit var Sub: Button
    lateinit var Mul: Button
    lateinit var Div: Button
    lateinit var Reset: Button
    lateinit var Action: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentA = FragmentA()
        if (null == savedInstanceState)
            supportFragmentManager.beginTransaction()
                .add(R.id.flayout1, fragmentA, "fA")
                .commit()
        else
            supportFragmentManager.beginTransaction()
                .replace(R.id.flayout1, FragmentA(), "fA")
                .commit()

    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//
//        super.onConfigurationChanged(newConfig)
//
//
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
//            Log.i("landscape", "toString()")
//
//
//
//        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
//            Log.i("portrait", "dfd")
//    }

}