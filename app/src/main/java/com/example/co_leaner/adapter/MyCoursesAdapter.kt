package com.example.co_leaner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.room.Course
import com.example.co_leaner.util.Utils

class MyCoursesAdapter(
    private val onClickListener: OnClickListener,
    val context: Context?
) :
    RecyclerView.Adapter<MyCoursesAdapter.MyCoursesViewHolder>(), Filterable {

    private val layoutInflater = LayoutInflater.from(context)
    private var myCourses = listOf<Course>()
    private var myCoursesFiltered = listOf<Course>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyCoursesViewHolder {
        val itemView = layoutInflater.inflate(R.layout.my_course_item, parent, false)
        return MyCoursesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyCoursesViewHolder, position: Int) {
        val myCourseItem = myCourses[position]
        Utils.setImage(context!!, myCourseItem.courseImage, holder.imgCourse)
        holder.txtClass.text = myCourseItem._class
        holder.txtCourseTitle.text = myCourseItem.courseTitle

        holder.itemView.setOnClickListener {
            onClickListener.onClick(myCourseItem)
        }
    }

    override fun getItemCount(): Int = myCourses.size

    class MyCoursesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgCourse: ImageView = itemView.findViewById(R.id.imgCourseImage)
        var txtCourseTitle: TextView = itemView.findViewById(R.id.txtCourseTitle)
        var txtClass: TextView = itemView.findViewById(R.id.txtClass)

        var viewForeGround: ConstraintLayout = itemView.findViewById(R.id.row_foreground)
        var viewBackground: LinearLayout = itemView.findViewById(R.id.row_background)
    }

    class OnClickListener(val clickListener: (course: Course) -> Unit) {
        fun onClick(course: Course) = clickListener(course)
    }

    fun submitData(myCourses: List<Course>) {
        this.myCourses = myCourses
        notifyDataSetChanged()
    }

    fun getCourse(position: Int): Course = myCourses[position]

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) myCoursesFiltered = myCourses else {
                    val filteredList = ArrayList<Course>()
                    myCourses
                        .filter {
                            (it.courseTitle?.contains(constraint!!))?.or(
                                (it.courseUrl?.contains(
                                    constraint!!
                                )!!)
                            )!!

                        }
                        .forEach { filteredList.add(it) }
                    myCoursesFiltered = filteredList

                }
                return FilterResults().apply { values = myCoursesFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                myCoursesFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Course>
                notifyDataSetChanged()
            }
        }
    }

}