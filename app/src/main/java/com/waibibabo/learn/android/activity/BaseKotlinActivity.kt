package com.waibibabo.learn.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.waibibabo.learn.android.annotation.Page

/**
 * @author  hehb
 * @date  2021/7/13 15:00
 */
@Page(name = "kotlin基础")
class BaseKotlinActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "BaseKotlinActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var i: Int = 1
        var str: String = if (i == 1) {
            "hehe"
        } else {
            "heihei"
        }
        str = when (i) {
            1 -> {
                println("这是1")
                "when"
            }
            else -> "when2"
        }
        i = add(1, 2)
        var innerFunc: (String, Int) -> Int = { q, _ -> q.length }
        funArg(TAG) { str ->
            println("callback $str")
        }
    }

    private fun add(a: Int, b: Int): Int {
        return a + b
    }

    private fun add2(a: Int, b: Int): Int = a + b

    private fun funArg(str: String, success: (String) -> Unit) {
        success(str)
    }
}