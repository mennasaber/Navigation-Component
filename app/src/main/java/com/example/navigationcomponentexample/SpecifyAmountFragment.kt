package com.example.navigationcomponentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText

class SpecifyAmountFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var recipient: String
    lateinit var amountEditTxt: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = requireArguments().getString("recipient")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.send_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        view.findViewById<TextView>(R.id.recipient).text = "Send money to $recipient"
        amountEditTxt = view.findViewById(R.id.input_amount)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.send_btn -> {
                if (amountEditTxt.text.toString().isNotEmpty()) {
                    val bundle =
                        bundleOf(
                            "recipient" to recipient,
                            "amount" to amountEditTxt.text.toString()
                        )
                    navController.navigate(
                        R.id.action_specifyAmountFragment_to_confirmationFragment,
                        bundle
                    )
                }

            }
            R.id.cancel_btn -> {
                requireActivity().onBackPressed()
            }
        }
    }
}