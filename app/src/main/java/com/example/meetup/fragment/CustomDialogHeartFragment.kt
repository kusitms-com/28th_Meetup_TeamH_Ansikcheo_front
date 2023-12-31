package com.example.meetup.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.DialogFragment
import com.example.meetup.R

class CustomDialogHeartFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        return inflater.inflate(R.layout.custom_dialog_heart, container,false)

        val customDialogHeartFragment = CustomDialogHeartFragment()
        customDialogHeartFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialogTheme)

//        customDialogHeartFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialogTheme)
//
//        customDialogHeartFragment.show(requireFragmentManager(),"CustomDialogHeartFragment")
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_dialog_heart, null)

        val btnX = view.findViewById<ImageView>(R.id.btn_x)
//        val btnShowHeartList = view.findViewById<TextView>(R.id.btn_show_heart_list)

        val goHeart = view.findViewById<TextView>(R.id.btn_show_heart_list)

        goHeart.setOnClickListener {
            val heartFragment = HeartFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea, heartFragment)
                commit()
                dismiss()
            }
        }

        builder.setView(view)
        btnX.setOnClickListener {
            // 예: 텍스트를 가져와 다른 작업 수행 또는 다이얼로그 닫기
            dismiss()
        }

        return builder.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}