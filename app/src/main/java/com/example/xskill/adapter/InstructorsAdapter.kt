package com.example.xskill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xskill.R
import com.example.xskill.model.Instructors
import com.example.xskill.util.Utils

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

        holder.itemView.setOnClickListener {
            onClickListener.onClick(instructor)
        }
    }

    override fun getItemCount(): Int = instructors.size

    class InstructorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgInstructor: ImageView = itemView.findViewById(R.id.imgInstructor)
        var txtDisplayName: TextView = itemView.findViewById(R.id.txtInstructorName)
        var txtJobTitle: TextView = itemView.findViewById(R.id.txtJobTitle)
    }

    class OnClickListener (val clickListener : (instructor: Instructors) -> Unit) {
        fun onClick(instructor: Instructors) = clickListener(instructor)
    }
}