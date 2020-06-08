package com.example.mykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baselibrary.recycleview.CommonAdapter
import com.example.baselibrary.recycleview.MultiItemTypeAdapter
import com.example.baselibrary.recycleview.ViewHolder
import com.example.mykotlin.bean.TestBean
import com.example.mykotlin.material.MaterialActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CommonAdapter<TestBean>
    private val list = ArrayList<TestBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnName.setOnClickListener {
            startActivity(Intent(this, MaterialActivity::class.java))
        }
    }



}
