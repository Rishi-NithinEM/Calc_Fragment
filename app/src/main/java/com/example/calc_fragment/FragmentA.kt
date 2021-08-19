package com.example.calc_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlin.concurrent.fixedRateTimer


class FragmentA : Fragment(), View.OnClickListener {

    lateinit var Add: Button
    lateinit var Sub: Button
    lateinit var Mul: Button
    lateinit var Div: Button
    lateinit var Reset: Button
    lateinit var Action: TextView

    var output: String = ""

    var operation: String = "x"

    var state: Int = 0

    override fun onClick(view: View) {

        var bundle: Bundle = Bundle()


        when (view.id) {
            R.id.add -> {
                operation = "Addition"
            }
            R.id.sub -> {
                operation = "Subtract"
            }
            R.id.mul -> {
                operation = "Multiply"
            }
            R.id.div -> {
                operation = "Division"
            }
        }


        bundle.putString("key", operation)

        var fragment: FragmentB = FragmentB()

        fragment.setArguments(bundle)

        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.flayout1, fragment)
            ?.addToBackStack("fA")?.commit()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflate = inflater.inflate(R.layout.fragment_a, container, false)

//        activity?.supportFragmentManager?.beginTransaction()
//            ?.replace(R.id.flayout1,FragmentA())
//            ?.addToBackStack("fA")?.commit()

        if(savedInstanceState!=null){
            operation=savedInstanceState.getString("opp","")
            state = savedInstanceState.getInt("state")
            output=savedInstanceState.getString("result","")

        }


        Add = inflate.findViewById(R.id.add)
        Sub = inflate.findViewById(R.id.sub)
        Mul = inflate.findViewById(R.id.mul)
        Div = inflate.findViewById(R.id.div)
        Reset = inflate.findViewById(R.id.reset)
        Action = inflate.findViewById(R.id.action)

        if (state == 0) {
            reset()
        } else {
            result()
        }

        Add.setOnClickListener(this)
        Sub.setOnClickListener(this)
        Mul.setOnClickListener(this)
        Div.setOnClickListener(this)

        Reset.setOnClickListener {


            reset()
            activity?.fragmentManager?.popBackStackImmediate()

        }

        return inflate

    }

    fun result() {

        state = 1
        Action.setText(output)
        Action.isVisible = true
        Reset.isVisible =  true
        Add.isVisible = false
        Sub.isVisible = false
        Mul.isVisible = false
        Div.isVisible = false

    }

    fun reset() {

        state = 0
        output = ""
        //Action.setText("")
        Action.isVisible = false
        Reset.isVisible =  false
        Add.isVisible = true
        Sub.isVisible = true
        Mul.isVisible = true
        Div.isVisible = true


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fragmentManager?.setFragmentResultListener(
            "Key",
            viewLifecycleOwner
        ) { st: String, bundle ->
            output = bundle.getString("and").toString()

                result()


        }
//        Toast.makeText(activity,"FragA",Toast.LENGTH_LONG).show()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("state", state)
        outState.putString("opp",operation)
        outState.putString("result",output)


    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        if (savedInstanceState != null) {
//            output=savedInstanceState.getString("result","")
//            state = savedInstanceState.getInt("state")
//            operation = savedInstanceState.getString("opp","")
//
//            if (state == 0)
//                reset()
//            else
//                result()
//        }
//
//
//    }


}

