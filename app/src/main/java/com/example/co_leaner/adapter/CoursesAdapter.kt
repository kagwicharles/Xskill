package com.example.co_leaner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.model.Courses
import com.example.co_leaner.util.Utils

class CoursesAdapter(
    private val onClickListener: OnClickListener,
    val context: Context?
) :
    PagingDataAdapter<Courses, CoursesAdapter.MyViewHolder>(DiffUtilCallBack) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyViewHolder {
        val itemView = layoutInflater.inflate(R.layout.course_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val courseItem: Courses? = getItem(position)
        Utils.setImage(context!!, courseItem?.image_240x135, holder.imgCourse)
        holder.txtCourse.text = courseItem?.title
        holder.txtClass.text = courseItem?._class
        holder.txtPrice.text = courseItem?.price

        holder.itemView.setOnClickListener {
            onClickListener.onClick(courseItem)
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgCourse: ImageView = itemView.findViewById(R.id.imgVCourse)
        var txtCourse: TextView = itemView.findViewById(R.id.txtvCourse)
        var txtClass: TextView = itemView.findViewById(R.id.txtClass)
        var txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Courses>() {
        override fun areItemsTheSame(oldItem: Courses, newItem: Courses): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Courses, newItem: Courses): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }
    }

    class OnClickListener(val clickListener: (courses: Courses?) -> Unit) {
        fun onClick(courses: Courses?) = clickListener(courses)
    }
}

