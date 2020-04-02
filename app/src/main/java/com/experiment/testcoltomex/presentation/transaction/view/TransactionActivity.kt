package com.experiment.testcoltomex.presentation.transaction.view

import android.os.Bundle
import android.view.View
import com.experiment.testcoltomex.R
import com.shopper.deli.presentation.core.base.BaseActivity

class TransactionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_transaction)
        supportActionBar?.hide()
    }

}
