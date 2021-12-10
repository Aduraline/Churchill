package africa.of.designers.churchill.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = ProgressDialog(this)
    }

    fun showLoadingDialog(message: String) {
        dialog.setMessage(message)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    fun logout() {
        ParseUser.logOut()
    }
}

