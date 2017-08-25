package com.example.picked.myplace.ui.place.favorite


import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.picked.myplace.R
import com.example.picked.myplace.repository.FavoritePlaceRepository
import com.example.picked.myplace.ui.place.PlaceListAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : LifecycleFragment(), FavoriteContract.View {

    private var presenter: FavoritePresenter? = null
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: PlaceListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        presenter = FavoritePresenter(this, FavoritePlaceRepository(), viewModel)
        adapter = PlaceListAdapter(viewModel.placeItemList, {
            presenter?.unFavorite(it)
        })
        favoriteRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        favoriteRecyclerView.adapter = adapter
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            presenter?.reloadFavorite()
        }
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }
}
