package com.example.calc_fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class FragmentB : Fragment(), View.OnClickListener {


    var ch: String? = ""
    var numb1: Int = 0
    var numb2: Int = 0
    var answer: Int = 0
    var Number1: String = ""
    var Number2: String = ""
    var result: String = ""
    lateinit var number1: EditText
    lateinit var number2: EditText
    lateinit var out: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate= inflater.inflate(R.layout.fragment_b, container, false)

        number1 = inflate.findViewById(R.id.Input1)
        number2 = inflate.findViewById(R.id.Input2)
        out = inflate.findViewById(R.id.output)

        ch = arguments?.getString("key")
        out.setText(ch)

        if(savedInstanceState != null) {
            val fm = activity?.supportFragmentManager
            val pf = fm?.findFragmentByTag("fA")
            if (pf != null) {
                fm.popBackStack()
                fm.beginTransaction().remove(pf).commit()
            }
        }


        return inflate
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {




        out.setOnClickListener(this)


    }

    override fun onClick(view: View) {

        Number1 = number1.text.toString()
        Number2 = number2.text.toString()

        if (Number1 != "" && Number2 != "") {
            numb1 = Number1.toInt()
            numb2 = Number2.toInt()

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
                    if (numb2 != 0)
                        answer = numb1 / numb2
                    else
                        result += "infinity"
                }
            }


            if (numb2 != 0)
                result += answer.toString()


            sendResult()


        } else {
            Toast.makeText(activity, "Please enter values", Toast.LENGTH_LONG).show()

        }

    }

    fun sendResult(){
        var bundle: Bundle = Bundle()

        bundle.putString("opp", ch)
        bundle.putString("ans", result)
        bundle.putString("in1", numb1.toString())
        bundle.putString("in2", numb2.toString())

        var fragment: FragmentA = FragmentA()

        fragment.setArguments(bundle)


        //fragmentManager?.setFragmentResult("Key",bundle)


        requireActivity().supportFragmentManager?.beginTransaction()?.replace(R.id.flayout1, fragment)
            ?.addToBackStack("fA")?.commit()

        //activity?.supportFragmentManager?.popBackStackImmediate()
    }

}



