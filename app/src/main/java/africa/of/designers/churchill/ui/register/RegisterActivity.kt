package africa.of.designers.churchill.ui.register

import africa.of.designers.churchill.R
import africa.of.designers.churchill.base.BaseActivity
import africa.of.designers.churchill.databinding.ActivityRegisterBinding
import africa.of.designers.churchill.ui.MainActivity
import africa.of.designers.churchill.ui.login.LoginActivity
import africa.of.designers.churchill.util.snackbar
import africa.of.designers.churchill.util.startNewActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

class RegisterActivity : BaseActivity(), RegisterListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.registerviewmodel = viewModel
        viewModel.registerListener = this
    }

    override fun onStarted() {
        showLoadingDialog("Signing up...")
    }

    override fun onSuccess() {
        dialog.dismiss()
        startNewActivity(MainActivity::class.java, true)
    }

    override fun onError(message: String, isProcessing: Boolean) {
        if(isProcessing)
            dialog.dismiss()
        window.decorView.rootView.snackbar(message, 1, this@RegisterActivity)
    }

    override fun onGotoLogin() {
        startNewActivity(LoginActivity::class.java, true)
    }
}
