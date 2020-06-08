package com.example.mykotlin.material

import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.baselibrary.BaseActivity
import com.example.mykotlin.R
import kotlinx.android.synthetic.main.activity_fruit.*

class FruitActivity : BaseActivity() {

    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_fruit
    }

    override fun initView() {
        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = fruitName
        Glide.with(this).load(fruitImageId).into(ivFruit)
        tvContent.text = generateFruitContent(fruitName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generateFruitContent(fruitName: String) = fruitName.repeat(500)
}
