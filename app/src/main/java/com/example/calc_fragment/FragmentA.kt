package com.example.calc_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible


class FragmentA : Fragment(R.layout.fragment_a), View.OnClickListener {

    lateinit var Add: Button
    lateinit var Sub: Button
    lateinit var Mul: Button
    lateinit var Div: Button
    lateinit var Reset: Button
    lateinit var Action: TextView
    lateinit var Input1: TextView
    lateinit var Input2: TextView
    lateinit var Result: TextView

    var operation: String = "x"

    override fun onClick(view: View) {

        var bundle: Bundle = Bundle()


        when (view.id) {
            R.id.add -> {
                operation = "Addition"
            }
            R.id.sub -> {
                operation = "Subtraction"
            }
            R.id.mul -> {
                operation = "Multiplication"
            }
            R.id.div -> {
                operation = "Division"
            }
        }


        bundle.putString("key", operation)

        var fragment: FragmentB = FragmentB()

        fragment.setArguments(bundle)


        this.onStop()
        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.flayout1, fragment,"Bss")
            ?.addToBackStack(null)?.commit()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Add = view.findViewById(R.id.add)
        Sub = view.findViewById(R.id.sub)
        Mul = view.findViewById(R.id.mul)
        Div = view.findViewById(R.id.div)
        Reset = view.findViewById(R.id.reset)
        Action = view.findViewById(R.id.action)
        Input1 = view.findViewById(R.id.Input1)
        Input2 = view.findViewById(R.id.Input2)
        Result = view.findViewById(R.id.result)


        Result.isVisible = false
        Input1.isVisible = false
        Input2.isVisible = false
        Action.isVisible = false
        Reset.isVisible = false

        Add.setOnClickListener(this)
        Sub.setOnClickListener(this)
        Mul.setOnClickListener(this)
        Div.setOnClickListener(this)


        var bundle: Bundle? = this.arguments
        if (bundle?.getString("ans") != null) {
            printAns(
                bundle?.getString("ans").toString(),
                bundle?.getString("in1").toString(),
                bundle?.getString("in2").toString(),
                bundle?.getString("opp").toString()
            )
        }

    }

    fun printAns(ans: String, input1: String, input2: String, opp: String) {

        Result.isVisible = true
        Input1.isVisible = true
        Input2.isVisible = true
        Action.isVisible = true
        Reset.isVisible = true
        Add.isVisible = false
        Sub.isVisible = false
        Mul.isVisible = false
        Div.isVisible = false

        Action.setText("Action: " + opp)
        Input1.setText("Input1: " + input1)
        Input2.setText("Input2: " + input2)
        Result.setText("Result: " + ans)

        Reset.setOnClickListener {


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


    }

}