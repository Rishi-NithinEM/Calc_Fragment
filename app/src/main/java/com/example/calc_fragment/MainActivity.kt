package com.example.calc_fragment

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (null == savedInstanceState) {
            fragmentA = FragmentA()
            supportFragmentManager.beginTransaction()
                .add(R.id.flayout1, fragmentA, "fA")
                .commit()
        }
        else {

            supportFragmentManager.beginTransaction()
                .replace(R.id.flayout1, FragmentA())
                .commit()

        }

    }



}