package dog.snow.androidrecruittest.ui.detailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.model.Detail
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import timber.log.Timber

class DetailsFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = this.activity as MainActivity
        setAppBar()

        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromListFragment()
    }

    private fun setAppBar() {
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mainActivity.supportActionBar?.setDisplayShowCustomEnabled(true)
        mainActivity.supportActionBar?.setDisplayShowHomeEnabled(false)
        mainActivity.title = "title"

        mainActivity.toolbar.setNavigationOnClickListener{
            onListFragment()
        }
    }

    private fun setListFragmentBar() {
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mainActivity.supportActionBar?.setDisplayShowCustomEnabled(false)
        mainActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        mainActivity.supportActionBar?.setIcon(R.drawable.ic_logo_sd_symbol)
        mainActivity.title = "  Android Recruit Test"
    }

    private fun onListFragment() {
        mainActivity.supportFragmentManager.popBackStack()
    }

    private fun getDataFromListFragment() {
        val detail: Detail? = this.arguments?.getParcelable("detail")
        Timber.e("DETAIL FRAGMENT $detail")
        if (detail != null) {
            Picasso.get()
                .load(detail.url)
                .into(iv_photo)
            tv_photo_title.text = detail.photoTitle
            tv_album_title.text = detail.albumTitle
            tv_username.text = detail.username
            tv_email.text = detail.email
            tv_phone.text = detail.phone
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        setListFragmentBar()
    }
}