package africa.of.designers.churchill.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.parse.ParseUser

class LoginViewModel : ViewModel() {

    var username: String? = null
    var password: String? = null

    var loginListener: LoginListener? = null

    fun onAuthenticateUser(view: View) {
        loginListener?.onStarted()
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            loginListener?.onError("No field should be left empty", true)
        } else {
            logIn(username, password)
        }
    }

    fun onGotoRegister(view: View) {
        loginListener?.onGotoRegister()
    }

    private fun logIn(username: String?, password: String?) {
        ParseUser.logInInBackground(username, password) { user, e ->
            if (user != null) {
                loginListener?.onSuccess()
            } else {
                loginListener?.onError(e.message.toString(), isProcessing = true)
            }
        }
    }
}
