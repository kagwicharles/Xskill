package com.example.co_leaner.adapter

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.co_leaner.R
import com.example.co_leaner.util.Utils
import com.example.co_leaner.viewmodel.CoursesViewModel

class SwipeHelperCallback(
    private val adapter: MyCoursesAdapter,
    private val viewModel: CoursesViewModel,
    private val context: Context?,
    private val fragment: Fragment
) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.RIGHT,
) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val course = adapter.getCourse(viewHolder.position)
        adapter.removeCourse(viewHolder.position)
        viewModel.deleteCourse(course)
        Utils.showSnackBar(
            context?.getString(R.string.delete_course),
            fragment.activity?.findViewById(R.id.mainLinearLayout)
        )
    }

    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val foregroundView: View =
            (viewHolder as MyCoursesAdapter.MyCoursesViewHolder).viewBackground
        getDefaultUIUtil().onDrawOver(
            c, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive
        )
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val foregroundView: View =
            (viewHolder as MyCoursesAdapter.MyCoursesViewHolder).viewForeGround

        getDefaultUIUtil().onDraw(
            c, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive
        )
    }
}