package africa.of.designers.churchill.util

import africa.of.designers.churchill.R
import africa.of.designers.churchill.data.model.ExtrasBoolean
import africa.of.designers.churchill.data.model.ExtrasInt
import africa.of.designers.churchill.data.model.ExtrasString
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.io.IOException

fun <A : Activity> Activity.startNewActivity(
    activity: Class<A>,
    shouldFinish: Boolean?
) {
    Intent(this, activity).also {
        if (shouldFinish == true)
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun <A : Activity> Activity.startNewActivityWithExtra(
    activity: Class<A>,
    shouldFinish: Boolean?,
    extraString: ArrayList<ExtrasString>?,
    extraInt: ArrayList<ExtrasInt>?,
    extraBoolean: ArrayList<ExtrasBoolean>?
) {
    Intent(this, activity).also {
        if (shouldFinish == true)
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        extraHandler(it, extraString, extraInt, extraBoolean)
        startActivity(it)
    }
}

fun extraHandler(
    it: Intent,
    extraString: ArrayList<ExtrasString>?,
    extraInt: ArrayList<ExtrasInt>?,
    extraBoolean: ArrayList<ExtrasBoolean>?
) {
    extraString?.forEach { c ->
        it.putExtra(
            c.key,
            c.value
        )
    }
    extraInt?.forEach { c ->
        it.putExtra(
            c.key,
            c.value
        )
    }
    extraBoolean?.forEach { c ->
        it.putExtra(
            c.key,
            c.value
        )
    }
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.9f
}

fun View.snackbar(message: String, type: Int?, context: Context) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    if (type == 1)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.darkRed))
    else
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
    snackbar.show()
}

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}