package africa.of.designers.churchill.ui.welcome

import africa.of.designers.churchill.R
import africa.of.designers.churchill.base.BaseActivity
import africa.of.designers.churchill.data.model.SplashModel
import africa.of.designers.churchill.ui.MainActivity
import africa.of.designers.churchill.ui.login.LoginActivity
import africa.of.designers.churchill.util.startNewActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.parse.ParseUser

class WelcomeActivity : BaseActivity() {
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_welcome)

        initViewModel()
        observeSplashLiveData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]
    }

    private fun observeSplashLiveData() {
        viewModel.initSplashScreen()
        val observer = Observer<SplashModel> {
            if (ParseUser.getCurrentUser() != null) {
                startNewActivity(MainActivity::class.java, true)
            } else {
                startNewActivity(LoginActivity::class.java, true)
            }
        }
        viewModel.liveData.observe(this, observer)
    }
}
