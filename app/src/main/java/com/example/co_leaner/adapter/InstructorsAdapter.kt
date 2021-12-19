package com.example.co_leaner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.model.Instructors
import com.example.co_leaner.util.Utils

class InstructorsAdapter(
    private val onClickListener: OnClickListener,
    val context: Context,
    private val instructors: ArrayList<Instructors>
) :
    RecyclerView.Adapter<InstructorsAdapter.InstructorViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructorViewHolder {
        val itemView = layoutInflater.inflate(R.layout.instructor_item, parent, false)
        return InstructorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InstructorViewHolder, position: Int) {
        val instructor = instructors[position]
        Utils.setImage(context, instructor.image_100x100, holder.imgInstructor)
        holder.txtDisplayName.text = instructor.title
        holder.txtJobTitle.text = instructor.job_title
        holder.txtUrl.text = context.getString(R.string.udemy_base_url, instructor.url)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(instructor)
        }
    }

    override fun getItemCount(): Int = instructors.size

    class InstructorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgInstructor: ImageView = itemView.findViewById(R.id.imgInstructor)
        var txtDisplayName: TextView = itemView.findViewById(R.id.txtInstructorName)
        var txtJobTitle: TextView = itemView.findViewById(R.id.txtJobTitle)
        var txtUrl: TextView = itemView.findViewById(R.id.txtInstructorUrl)
    }

    class OnClickListener (val clickListener : (instructor: Instructors) -> Unit) {
        fun onClick(instructor: Instructors) = clickListener(instructor)
    }
}