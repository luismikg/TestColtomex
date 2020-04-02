package com.experiment.testcoltomex.presentation.transaction.viewmodal

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class TransactionViewModel : ViewModel() {

    val status = MutableLiveData<Int>()
    val topay = ObservableField<String>("")
    val serviceCost = ObservableField<String>("")
    val total = ObservableField<String>("")
    val message = ObservableField<String>("")
    val buttonEnable = ObservableField<Boolean>(false)

    fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        var strTopay = s.toString()
        strTopay = strTopay.replace(",", "")

        getServiceCost(strTopay)
    }

    fun onClickClose() {
        status.value = 0
    }

    fun onClickMakeTransaction() {
        status.value = 1
    }

    fun clearAll() {
        topay.set("")
        serviceCost.set("")
        total.set("")
        message.set("")

        buttonEnable.set(false)
    }

    private fun getServiceCost(strTopay: String) {

        if (strTopay.length == 0) {
            serviceCost.set("")
            buttonEnable.set(false)

            getSTotal(strTopay, "")
        } else {
            val strServiceCost = (strTopay.toDouble() * 10 / 100).toString()
            serviceCost.set("$" + currencyFormat(strServiceCost))

            getSTotal(strTopay, strServiceCost)

            buttonEnable.set(true)
        }
    }

    private fun getSTotal(strTopay: String, strServiceCost: String) {

        if (strTopay.length == 0) {
            total.set("")
            return
        }
        if (strServiceCost.length == 0) {
            total.set("")
            return
        } else {
            val totalD = strTopay.toDouble() + strServiceCost.toDouble()
            total.set("$" + currencyFormat(totalD.toString()))
        }
    }

    fun currencyFormat(amount: String): String {
        val formatter = DecimalFormat("###,###,##0.00")
        return formatter.format(amount.toDouble())
    }
}