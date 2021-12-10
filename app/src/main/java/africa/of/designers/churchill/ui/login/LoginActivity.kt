package africa.of.designers.churchill.ui.login

import africa.of.designers.churchill.R
import africa.of.designers.churchill.base.BaseActivity
import africa.of.designers.churchill.databinding.ActivityLoginBinding
import africa.of.designers.churchill.ui.MainActivity
import africa.of.designers.churchill.ui.register.RegisterActivity
import africa.of.designers.churchill.util.snackbar
import africa.of.designers.churchill.util.startNewActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

class LoginActivity : BaseActivity(), LoginListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.loginviewmodel = viewModel
        viewModel.loginListener = this
    }

    override fun onStarted() {
        showLoadingDialog("Logging in...")
    }

    override fun onSuccess() {
        dialog.dismiss()
        startNewActivity(MainActivity::class.java, true)
    }

    override fun onError(message: String, isProcessing: Boolean) {
        if(isProcessing)
            dialog.dismiss()
        window.decorView.rootView.snackbar(message, 1, this@LoginActivity)
    }

    override fun onGotoRegister() {
        startNewActivity(RegisterActivity::class.java, true)
    }

}
