package dog.snow.androidrecruittest

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer

import dog.snow.androidrecruittest.ui.listFragment.ListFragment
import dog.snow.androidrecruittest.utils.MainFragmentFactory
import dog.snow.androidrecruittest.utils.NetworkConnection
import kotlinx.android.synthetic.main.layout_banner.*
import kotlinx.android.synthetic.main.list_fragment.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MainFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        banner.textAlignment = View.TEXT_ALIGNMENT_CENTER

        initListFragment()
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

    private fun initListFragment() {
        if (supportFragmentManager.fragments.size == 0) {
            val bundle = Bundle()
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerView, ListFragment::class.java, bundle)
                .commit()
        }
    }
}