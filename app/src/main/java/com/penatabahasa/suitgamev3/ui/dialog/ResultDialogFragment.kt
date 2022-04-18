package com.penatabahasa.suitgamev3.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.penatabahasa.suitgamev3.R
import com.penatabahasa.suitgamev3.controller.CallbackDialog
import java.lang.Exception

class ResultDialogFragment : DialogFragment() {
    private var callDialog: CallbackDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnRematch = view.findViewById<Button>(R.id.btnRematch)
        val resultMatch = view.findViewById<TextView>(R.id.tvResultGame)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        btnRematch.setOnClickListener {
            dismiss()
            callDialog?.restartGame(android.R.color.transparent, "", "")
        }
        if (arguments != null){
            val result = arguments?.getString("result", "")
            resultMatch.text = result
        }

        btnBack.setOnClickListener {
            activity?.finish()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callDialog = context as CallbackDialog
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}