package dog.snow.androidrecruittest.ui.splashActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.R
import kotlinx.android.synthetic.main.layout_progressbar.*
import timber.log.Timber
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

        viewModel.error.observe(this, Observer {
            Timber.e("Error: $it")
            if (it == true) {
                showError("I can't download the data")
            }
        })

        viewModel.loading.observe(this, Observer {
            Timber.e("Loading: $it")
            if (it == true) {
                progressbar.visibility = View.VISIBLE
            }
            else if (it == false) {
                progressbar.visibility = View.GONE
                onListFragment()
            }
        })
    }

    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> /*tryAgain()*/ }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }

    private fun onListFragment() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
        finish()
    }
}
