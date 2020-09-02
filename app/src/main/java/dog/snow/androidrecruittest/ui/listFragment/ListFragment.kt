package dog.snow.androidrecruittest.ui.listFragment

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.data.model.RawAlbum
import dog.snow.androidrecruittest.data.model.RawPhoto
import dog.snow.androidrecruittest.data.model.RawUser
import dog.snow.androidrecruittest.ui.detailsFragment.DetailsFragment
import dog.snow.androidrecruittest.ui.adapter.ListAdapter
import dog.snow.androidrecruittest.ui.model.Detail
import dog.snow.androidrecruittest.ui.model.ListItem
import kotlinx.android.synthetic.main.layout_search.view.*
import kotlinx.android.synthetic.main.list_fragment.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel: ListViewModel
    private lateinit var listAdapter: ListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var userList: List<RawUser> = ArrayList()
    private var itemList: List<ListItem> = ArrayList()
    private var albumsList: List<RawAlbum> = ArrayList()
    private var photosList: List<RawPhoto> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        mainActivity = this.activity as MainActivity

        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData()
        setUpBar()

        view?.et_search?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })


    }

    private fun filter(text: String) {
        var filteredList: ArrayList<ListItem> = ArrayList()
        for (item: ListItem in itemList) {
            if (item.title.toLowerCase(Locale.getDefault()).contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        listAdapter.filterList(filteredList)
    }

    private fun initRecyclerView() {
        rv_items.apply {
            viewManager = LinearLayoutManager(requireContext())
            listAdapter = ListAdapter()
            adapter = listAdapter
            setHasFixedSize(true)
        }

        listAdapter.onItemClick = {
            setDetail(it.id)
        }
    }

    private fun onDetailsFragment(detail: Detail) {
        val bundle = Bundle()
        bundle.putParcelable("detail", detail)
       // val detailsFragment =
        //    DetailsFragment()
        //detailsFragment.arguments = bundle
        //mainActivity.supportFragmentManager.beginTransaction()
             mainActivity?.supportFragmentManager?.beginTransaction()
            .replace(R.id.containerView, DetailsFragment::class.java, bundle)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onAttach(activity: Activity) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    private fun getData() {                                                                          //Get the lists of data from the ViewModel
        viewModel.getPhotosCache().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                photosList = it
                setItemList()
            }
        })

        viewModel.getAlbumsCache()
            .observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    albumsList = it
                    setItemList()
                }
            })

        viewModel.getUsersCache()
            .observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    userList = it
                }
            })
    }

    private fun setItemList() {
        if (albumsList.isNotEmpty() && photosList.isNotEmpty()) {
                                                                                                          //Merging the lists of RawAlbum and
            val albumsById: Map<Int, RawAlbum> = albumsList.associateBy { it.id }                        // RawPhoto in the one ListItem list.

            itemList = photosList.filter { albumsById[it.id] != null }.map { photosList ->
                albumsById[photosList.id]?.let { albumsList ->
                    ListItem(albumsList.id, albumsList.title, photosList.title, photosList.thumbnailUrl)
                }!!
            }

            if (itemList.isNotEmpty()) {
                rv_items.visibility = View.VISIBLE
                empty_list.visibility = View.GONE
                initRecyclerView()
                listAdapter.submitList(itemList)

            }
        }
    }

    private fun setDetail(id: Int) {                                                                   //Set the Detail object and send to the
        if (albumsList.isNotEmpty() && photosList.isNotEmpty() && userList.isNotEmpty()) {             //DetailsFragment

            if (id != null) {
                var photo = photosList[id]
                var user = userList[id]
                var album = albumsList[id]

                var detail = Detail(photo.id, photo.title, user.username, album.title, user.email, user.phone, photo.url)

                onDetailsFragment(detail)
            }
        }
    }

    private fun setUpBar() {
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.setIcon(R.drawable.ic_logo_sd_symbol)
        mainActivity.title = "  Android Recruit Test"
    }

}