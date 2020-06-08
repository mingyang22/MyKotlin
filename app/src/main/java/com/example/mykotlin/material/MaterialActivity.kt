package com.example.mykotlin.material

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.baselibrary.BaseActivity
import com.example.baselibrary.extension.showSnackBar
import com.example.baselibrary.extension.showToast
import com.example.baselibrary.recycleview.CommonAdapter
import com.example.baselibrary.recycleview.MultiItemTypeAdapter
import com.example.baselibrary.recycleview.ViewHolder
import com.example.mykotlin.R
import com.example.mykotlin.bean.Fruit
import kotlinx.android.synthetic.main.activity_material.*
import kotlin.concurrent.thread

class MaterialActivity : BaseActivity() {

    private val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )

    val fruitList = ArrayList<Fruit>()

    override fun getLayoutId(): Int {
        return R.layout.activity_material
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        fb.setOnClickListener {
            it.showSnackBar("Data deleted", "Undo") {
                "Data restored".showToast(this)
            }
        }

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        /**
         * 使用 MaterialCardView 必须使用 Theme.MaterialComponents 或其子类，否则会报错
         */
        val adapter = object : CommonAdapter<Fruit>(this, R.layout.item_fruit, fruitList) {
            override fun convert(holder: ViewHolder, data: Fruit, position: Int) {
                holder.setText(R.id.tvName, data.name)
                val view = holder.getView(R.id.ivFruit) as ImageView
                Glide.with(this@MaterialActivity).load(data.imageId).into(view)
            }
        }
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener { _, position ->
            val fruit = fruitList[position]
            val intent = Intent(this@MaterialActivity, FruitActivity::class.java).apply {
                putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
            }
            this@MaterialActivity.startActivity(intent)

        }
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    private fun refreshFruits(adapter: CommonAdapter<Fruit>) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }

    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> "Backup".showToast(this)
            R.id.delete -> "Delete".showToast(this)
            R.id.settings -> "Settings".showToast(this)
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

}
