package com.bezzo.core.extension

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.snackbar(message : String, length : Int = Snackbar.LENGTH_SHORT){
    val snackbar = Snackbar.make(this,
            message, length)
    snackbar.show()
}

fun View.snackbar(message: String, length: Int = Snackbar.LENGTH_INDEFINITE, actionText: String,
                           action: () -> Unit = {}) {
    val snackBar = Snackbar.make(this, message, length)
    snackBar.setAction(actionText){ action.invoke() }
    snackBar.show()
}

fun pxToDp(px: Float): Int {
    val metrics = Resources.getSystem().displayMetrics
    val dp = px / (metrics.densityDpi / 160f)
    return Math.round(dp)
}

fun dpToPx(dp: Float): Int {
    val metrics = Resources.getSystem().displayMetrics
    val px = dp * (metrics.densityDpi / 160f)
    return Math.round(px)
}

fun EditText.isEmpty(): Boolean {
    val str = text.toString()

    return when {
        str.isEmpty() -> {
            error = "Field cannot be blank"
            requestFocus()
            true
        }
        else -> false
    }
}

fun EditText.isEmailInvalid(): Boolean {
    val str = text.toString()

    return when {
        str.isEmpty() -> {
            error = "Email cannot be blank"
            requestFocus()
            true
        }
        !Patterns.EMAIL_ADDRESS.matcher(str).matches() -> {
            error = "Invalid email address."
            requestFocus()
            true
        }
        else -> false
    }
}

fun EditText.isPasswordInvalid(): Boolean {
    val str = text.toString()

    return when {
        str.isEmpty() -> {
            error = "Password cannot be blank"
            requestFocus()
            true
        }
        str.length < 6 -> {
            error = "Password must be at least 6 characters"
            requestFocus()
            true
        }
        else -> false
    }
}

fun EditText.isPasswordNotMatch(firstPass: EditText): Boolean {
    val str = text.toString()
    val pass = firstPass.text.toString()

    return when {
        str.isEmpty() -> {
            error = "Password cannot be blank"
            requestFocus()
            true
        }
        str.length < 6 -> {
            error = "Password must be at least 6 characters"
            requestFocus()
            true
        }
        str != pass -> {
            error = "Password do not match."
            requestFocus()
            true
        }
        else -> false
    }
}

fun TextInputLayout.isEmpty(): Boolean {
    val str = editText?.text.toString()

    return when {
        str.isEmpty() -> {
            error = "Field cannot be blank"
            requestFocus()
            true
        }
        else -> {
            error = ""
            false
        }
    }
}

fun TextInputLayout.isEmailInvalid(): Boolean {
    val str = editText?.text.toString()

    return when {
        str.isEmpty() -> {
            error = "Email cannot be blank"
            requestFocus()
            true
        }
        !Patterns.EMAIL_ADDRESS.matcher(str).matches() -> {
            error = "Invalid email address."
            requestFocus()
            true
        }
        else -> {
            error = ""
            false
        }
    }
}

fun TextInputLayout.isPasswordInvalid(): Boolean {
    val str = editText?.text.toString()

    return when {
        str.isEmpty() -> {
            error = "Password cannot be blank"
            requestFocus()
            true
        }
        str.length < 6 -> {
            error = "Password must be at least 6 characters"
            requestFocus()
            true
        }
        else -> {
            error = ""
            false
        }
    }
}

fun TextView.setHtml(text: String?) {
    text?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
        } else {
            setText(Html.fromHtml(text))
        }
    }
}

fun TextView.setTextWithDifferentColor(text: Array<String>, color: Array<Int>) {
    if (text.size != color.size) return

    val builder = SpannableStringBuilder()

    for (i in text.indices) {
        val span = SpannableString(text[i])
        span.setSpan(ForegroundColorSpan(ContextCompat.getColor(this.context, color[i])),
                0, text[i].length, 0)
        builder.append(span)
    }

    setText(builder, TextView.BufferType.SPANNABLE)
}

fun FloatingActionButton.autoHide(recyclerViewAxis : Int){
    if (recyclerViewAxis > 0 && this.visibility == View.VISIBLE) {
        this.hide()
    } else if (recyclerViewAxis < 0 && this.visibility != View.VISIBLE) {
        this.show()
    }
}