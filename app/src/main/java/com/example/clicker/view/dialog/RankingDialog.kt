package com.example.clicker.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.clicker.R
import com.example.clicker.util.RankingDto


class RankingDialog(
    context: Context,
    val rankingList : ArrayList<RankingDto>,
    val clearCallback : () -> Unit,
    val copyCallback : () -> Unit
) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_ranking)
        this.setCancelable(false)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        var rankingText = ""
        rankingList.forEachIndexed { index, rankingDto ->
            rankingText += "${index+1}.${rankingDto.name} : ${rankingDto.plus} ${rankingDto.minus} ${rankingDto.total}\n"
        }
        findViewById<TextView>(R.id.rankingText).text = rankingText
        findViewById<ImageView>(R.id.resetButton).setOnClickListener {
            findViewById<TextView>(R.id.rankingText).text = ""
            clearCallback()
        }
        findViewById<TextView>(R.id.cancelBtn).setOnClickListener {
            this.cancel()
        }
        findViewById<TextView>(R.id.copyButton).setOnClickListener {
            copyCallback()
        }

    }
}