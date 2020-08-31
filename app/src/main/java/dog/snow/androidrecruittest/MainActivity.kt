package dog.snow.androidrecruittest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dog.snow.androidrecruittest.ui.listFragment.IOnBackPressed
import dog.snow.androidrecruittest.utils.NetworkConnection
import kotlinx.android.synthetic.main.layout_banner.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        banner.textAlignment = View.TEXT_ALIGNMENT_CENTER

        checkInternetConnection()

        NetworkConnection(applicationContext?: return)
            .observe(this, Observer { isConnected ->
                if (!isConnected) {
                    banner.visibility = View.GONE
                    return@Observer
                } else {
                    banner.visibility = View.VISIBLE
                    return@Observer
                }
            })
    }

    private fun checkInternetConnection() {
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, Observer { isConnected ->
            if (!isConnected) {
                banner.visibility = View.GONE
                return@Observer
            } else {
                banner.visibility = View.VISIBLE

            }
        })
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.containerView)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }
}