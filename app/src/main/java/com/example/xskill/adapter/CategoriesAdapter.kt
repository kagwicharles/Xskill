package com.example.xskill.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.xskill.R
import com.example.xskill.model.CourseCategories
import kotlin.random.Random

class CategoriesAdapter(
    private val onClickListener: CategoriesAdapter.OnClickListener,
    context: Context?,
    var categoriesList: ArrayList<CourseCategories>
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = layoutInflater.inflate(R.layout.category_item, parent, false)
        return CategoriesViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val courseCategory = categoriesList[position]
        holder.imgItem?.setImageResource(courseCategory.categoryImage)
        holder.txtCategory?.text = courseCategory.categoryName

        holder.cardView?.setCardBackgroundColor(getColorArray()[position])

        holder.itemView.setOnClickListener {
            onClickListener.onClick(courseCategory)
        }
    }

    override fun getItemCount(): Int = categoriesList.size

    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: ImageView? = itemView.findViewById(R.id.imgCategory)
        val txtCategory: TextView? = itemView.findViewById(R.id.txtCategory)
        val cardView: CardView? = itemView.findViewById(R.id.categoryCard)
    }

    private fun generateRandomColor(): Int =
        Color.rgb(100, Random.nextInt(50, 100), Random.nextInt(255))

    class OnClickListener(val clickListener: (courseCategory: CourseCategories) -> Unit) {
        fun onClick(courseCategory: CourseCategories) = clickListener(courseCategory)
    }

    private fun getColorArray(): List<Int> = listOf(
        -10199983,
        -10209679,
        -10204732,
        -10200464,
        -10210440,
        -10204214,
        -10205833,
        -10199578,
        -10203787,
        -10207647,
        -10198588,
        -10205541
    )
}