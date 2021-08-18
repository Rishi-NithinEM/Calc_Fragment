package com.example.calc_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class FragmentB : Fragment(R.layout.fragment_b), View.OnClickListener {


    var ch: String? = ""
    var numb1: Double = 0.0
    var numb2: Double = 0.0
    var answer: Double = 0.0
    var Number1: String = ""
    var Number2: String = ""
    var result: String = ""
    lateinit var number1: EditText
    lateinit var number2: EditText
    lateinit var out: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        number1 = view.findViewById(R.id.Input1)
        number2 = view.findViewById(R.id.Input2)
        out = view.findViewById(R.id.output)


        var bundle: Bundle? = this.arguments
        ch = bundle?.getString("key")

        out.setText(ch)

        out.setOnClickListener(this)


    }

    override fun onClick(view: View) {

        Number1 = number1.text.toString()
        Number2 = number2.text.toString()

        if (Number1 != "" && Number2 != "") {
            numb1 = Number1.toDouble()
            numb2 = Number2.toDouble()

            when (ch) {
                "Addition" -> {
                    answer = numb1 + numb2
                }
                "Subtract" -> {
                    answer = numb1 - numb2
                }
                "Multiply" -> {
                    answer = numb1 * numb2
                }
                "Division" -> {
                    if (numb2 != 0.0)
                        answer = numb1 / numb2
                    else
                        result += "infinity"
                }
            }


            if (numb2 != 0.0)
                result += answer.toString()


            var bundle: Bundle = Bundle()

            bundle.putString("opp", ch)
            bundle.putString("ans", result)
            bundle.putString("in1", numb1.toString())
            bundle.putString("in2", numb2.toString())

            var fragment: FragmentA = FragmentA()

            fragment.setArguments(bundle)


            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.flayout1, fragment, "Add")
                ?.commit()
        } else {
            Toast.makeText(activity, "Please enter values", Toast.LENGTH_LONG).show()

        }

    }

}