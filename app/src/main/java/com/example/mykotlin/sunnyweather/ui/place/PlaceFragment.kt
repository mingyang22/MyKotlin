package com.example.mykotlin.sunnyweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.extension.showToast
import com.example.baselibrary.recycleview.CommonAdapter
import com.example.baselibrary.recycleview.ViewHolder
import com.example.mykotlin.R
import com.example.mykotlin.sunnyweather.logic.model.Place
import kotlinx.android.synthetic.main.fragment_place.*

/**
 * @author yang on 2020/6/7
 */
class PlaceFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: CommonAdapter<Place>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = object : CommonAdapter<Place>(activity!!, R.layout.item_place) {
            override fun convert(holder: ViewHolder, data: Place, position: Int) {
                holder.setText(R.id.tvPlaceName, data.name)
                holder.setText(R.id.tvPlaceAddress, data.address)
            }
        }
        recyclerView.adapter = adapter
        edtSearchPlace.addTextChangedListener {
            val content = it.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                recyclerView.visibility = View.GONE
                ivBg.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(this, Observer {
            val places = it.getOrNull()
            if (places != null) {
                recyclerView.visibility = View.VISIBLE
                ivBg.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                R.string.no_place.showToast(activity!!)
                it.exceptionOrNull()?.printStackTrace()

            }
        })


    }

}