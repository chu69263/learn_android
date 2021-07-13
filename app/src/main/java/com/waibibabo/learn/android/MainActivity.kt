package com.waibibabo.learn.android

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waibibabo.learn.android.annotation.Page

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = PageAdapter(getAllPage())
    }

    private fun getAllPage(): List<PageData> {
        val list = arrayListOf<PageData>()
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        packageInfo.activities.forEach {
            val clazz = Class.forName(it.name)
            val annotation = clazz.getAnnotation(Page::class.java)
            if (annotation != null) {
                list.add(PageData(clazz as Class<out Activity>, annotation))
            }
        }
        return list
    }
}

class PageData(val pageClass: Class<out Activity>, val info: Page)

class PageAdapter(private val items: List<PageData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return PageViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.findViewById<TextView>(R.id.tv).text = item.info.name
        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(
                    Intent(holder.itemView.context, item.pageClass)
            )
        }
    }
}

class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)