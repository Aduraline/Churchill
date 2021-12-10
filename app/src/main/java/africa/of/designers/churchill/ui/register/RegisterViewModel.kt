package africa.of.designers.churchill.ui.register

import android.view.View
import androidx.lifecycle.ViewModel
import com.parse.ParseUser

class RegisterViewModel : ViewModel() {
    var username: String? = null
    var email: String? = null
    var password: String? = null

    var registerListener: RegisterListener? = null

    fun onAuthenticateUser(view: View) {
        registerListener?.onStarted()
        if (username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) {
            registerListener?.onError("No field should be left empty", true)
        } else {
            signUp(username, email, password)
        }
    }

    fun onGotoLogin(view: View) {
        registerListener?.onGotoLogin()
    }

    private fun signUp(username: String?, email: String?, password: String?) {
        val user = ParseUser()
        user.username = username
        user.setPassword(password)
        user.email = email

        user.signUpInBackground { e ->
            if (e == null) {
                registerListener?.onSuccess()
            } else {
                registerListener?.onError(e.message.toString(), true)
            }
        }
    }
}
