package com.bezzo.core.extension

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import com.bezzo.core.R
import java.util.*
import java.util.regex.Pattern


object AppExtensions {

    fun getAppVersion(context: Context) : String? {
        return try {
            val packageInfo = context.packageManager?.getPackageInfo(context.packageName, 0)
            packageInfo?.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "Gagal menampilkan App Version"
        }
    }

    fun openBirthDatePicker(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(textView.context, DatePickerDialog.OnDateSetListener { view, _, _, _ ->
            val mMonth: String? = if (view.month < 9) {
                "0${view.month + 1}"
            } else {
                "${view.month + 1}"
            }

            val mDate: String? = if (view.dayOfMonth <= 9) {
                "0${view.dayOfMonth}"
            } else {
                "${view.dayOfMonth}"
            }

            val dateResult = "${view.year}-$mMonth-$mDate"
            textView.text = dateResult
        }, year, month, day)

        calendar.add(Calendar.DATE, 0)
        dpd.datePicker.maxDate = calendar.timeInMillis
        dpd.show()
    }

    fun openKunjunganDatePicker(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(textView.context, DatePickerDialog.OnDateSetListener { view, _, _, _ ->
            val mMonth: String? = if (view.month < 9) {
                "0${view.month + 1}"
            } else {
                "${view.month + 1}"
            }

            val mDate: String? = if (view.dayOfMonth <= 9) {
                "0${view.dayOfMonth}"
            } else {
                "${view.dayOfMonth}"
            }

            val dateResult = "${view.year}-$mMonth-$mDate"
            textView.text = dateResult
        }, year, month, day)

        calendar.add(Calendar.DATE, 0)
        dpd.datePicker.minDate = calendar.timeInMillis
        calendar.add(Calendar.DATE, 1)
        dpd.datePicker.maxDate = calendar.timeInMillis
        dpd.show()
    }

    fun timePickerDialog(textView: TextView) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE) + 10
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(textView.context, TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
            textView.text = selectedHour.toString() + ":" + selectedMinute
        }, hour, minute, false)

        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
    }

    fun hideKeyboard(activity: Activity?) {

        var inputMethodManager: InputMethodManager?
        activity?.let {
            inputMethodManager = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

            //Find the currently focused view, so we can grab the correct window token from it.
            var view = it.currentFocus
            /* If no view currently has focus, create a new one, just so we can grab a window token from it */
            if (view == null) {
                view = View(activity)
            }
            inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)

        }
    }

    fun checkInputStatus(editText: EditText) : Boolean {
        return !(editText.text.isNullOrBlank() || editText.text.isNullOrEmpty())
    }

    fun isEmailValid(email: String): Boolean {
        val regExpn = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

        val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)

        return matcher.matches()
    }

    fun showProgressDialog(context: Context, message : String = "") : AlertDialog {
        val progressDialog = AlertDialog.Builder(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
        layout.findViewById<AppCompatTextView>(R.id.tv_loading).text = message
        progressDialog.setView(layout)
        return progressDialog.create()
    }
}