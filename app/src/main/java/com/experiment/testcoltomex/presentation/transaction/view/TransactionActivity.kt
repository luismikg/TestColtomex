package com.experiment.testcoltomex.presentation.transaction.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.experiment.testcoltomex.R
import com.experiment.testcoltomex.databinding.ActivityTransactionBinding
import com.experiment.testcoltomex.presentation.transaction.viewmodal.TransactionViewModel
import com.experiment.testcoltomex.presentation.transaction.viewmodal.TransactionViewModelFactory
import com.shopper.deli.presentation.core.base.BaseActivity

class TransactionActivity : BaseActivity() {

    private var activityTransactionBinding: ActivityTransactionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        super.onCreate(savedInstanceState)

        activityTransactionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_transaction)
        activityTransactionBinding?.viewmodel = ViewModelProvider(
            this,
            TransactionViewModelFactory()
        ).get(TransactionViewModel::class.java)

        activityTransactionBinding?.viewmodel?.status?.observe(
            this,
            Observer {
                it?.let { statusEvent -> checkStatusEventPopupPrinter(statusEvent) }
            }
        )

        supportActionBar?.hide()
    }

    private fun checkStatusEventPopupPrinter(status: Int) {

        when (status) {
            0 -> closeApp()
            1 -> successTransacion()
        }
    }

    private fun closeApp() {
        finish()
    }

    private fun successTransacion() {
        activityTransactionBinding?.viewmodel?.clearAll()
    }

}
