package com.example.chatsdk.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatsdk.interfaces.OnBaseListener
import java.util.*


open class BaseActivity : AppCompatActivity(),
    OnBaseListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
    }

    override fun showMessage(mData: String) {
        showToast(mData)
    }

    override fun showMessage(mStringID: Int) {
        showToast(getString(mStringID))
    }

    override fun startNewActivity(mIntent: Intent, cls: Class<*>) {
        mIntent.setClass(this, cls)
        startActivity(mIntent)
    }

    override fun doAction(action: Int, data: Any) {

    }

    fun showToast(msg: String) {
        if (msg.isNotEmpty()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    open fun convertCount(value: Int): String? {
        val suffixes: NavigableMap<Long, String> = TreeMap()
        suffixes.put(1_000L, "k")
        suffixes.put(1_000_000L, "M")
        suffixes.put(1_000_000_000L, "G")
        suffixes.put(1_000_000_000_000L, "T")
        suffixes.put(1_000_000_000_000_000L, "P")
        suffixes.put(1_000_000_000_000_000_000L, "E")
        if (value < 1000) return java.lang.Long.toString(value.toLong()) //deal with easy case
        val e: Map.Entry<Long, String> = suffixes.floorEntry(value.toLong())
        val divideBy = e.key
        val suffix = e.value
        val truncated: Long = value / (divideBy / 10) //the number part of the output times 10
        val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
        return if (hasDecimal) (truncated / 10.0).toString() + suffix else (truncated / 10).toString() + suffix

    }

    fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}