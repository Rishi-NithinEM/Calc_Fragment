package com.example.calc_fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel


class SharedViewMode : ViewModel(){


}


class FragmentB : Fragment() {


    var ch: String? = ""
    var numb1: Double = 0.0
    var numb2: Double = 0.0
    var answer: Double = 0.0
    var Number1: String = ""
    var Number2: String = ""
    var result: String = ""
    lateinit var number1: EditText
    lateinit var number2: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_b, container, false)

//        Toast.makeText(activity,"FragB",Toast.LENGTH_LONG).show()

        number1 = inflate.findViewById(R.id.Input1)
        number2 = inflate.findViewById(R.id.Input2)
        val out: Button = inflate.findViewById(R.id.output)

        ch = arguments?.getString("key").toString()


        if (savedInstanceState != null) {

            val fm = activity?.supportFragmentManager
            val pf = fm?.findFragmentByTag("fA")
            if (pf != null) {
                fm.popBackStack()
                fm.beginTransaction().remove(pf).commit()

                val pf= FragmentB()
                val bundle :Bundle = Bundle()
                bundle?.putString("val1",savedInstanceState.getString("val1"))
                bundle?.putString("val2",savedInstanceState.getString("val2"))
                bundle?.putString("key",savedInstanceState.getString("key"))
                pf.setArguments(bundle)

                    activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.flayout1, pf)
                    ?.addToBackStack("fA")?.commit()


            }
        }
        number1.setText(arguments?.getString("val1"))
        number2.setText(arguments?.getString("val2"))
        out.setText(ch)

        out.setOnClickListener {
            Number1 = number1.text.toString()
            Number2 = number2.text.toString()

            if (generateResult(Number1, Number2) != "") {
                sendResult()
            }

        }

        return inflate
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("key", ch)
        if(number1.text!=null)
        outState.putString("val1", number1.text.toString())
        if(number2.text!=null)
        outState.putString("val2", number2.text.toString())
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//
//        if (savedInstanceState != null) {
//            ch = savedInstanceState.getString("key")
//            number1.setText(savedInstanceState.getString("val1"))
//            number2.setText(savedInstanceState.getString("val2"))
//        }
//    }

    fun generateResult(n1: String, n2: String): String {

        if (n1 != "" && n2 != "") {
            numb1 = n1.toDouble()
            numb2 = n2.toDouble()

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


            return result


        } else {
            Toast.makeText(activity, "Please enter values", Toast.LENGTH_LONG).show()
            return ""
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        out.setText(ch)
    }


    fun sendResult() {

        var res = "Action: " + ch + "\nInput1: " + numb1 + "\nInput2: " + numb2 + "\nResult: " + result

//        Toast.makeText(activity,result,Toast.LENGTH_LONG).show()


        fragmentManager?.setFragmentResult("Key", bundleOf("and" to res))
        fragmentManager?.popBackStackImmediate()


    }

}



