package com.example.co_leaner.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.View.GONE
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.co_leaner.R
import com.example.co_leaner.adapter.InstructorsAdapter
import com.example.co_leaner.databinding.FragmentCourseDetailBinding
import com.example.co_leaner.model.Courses
import com.example.co_leaner.network.CoursesApiService
import com.example.co_leaner.room.Course
import com.example.co_leaner.room.MyCoursesRepo
import com.example.co_leaner.util.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!

    private var courseId: Int? = null
    private var courseClass: String? = null
    private var courseTitle: String? = null
    private var courseImage: String? = null
    private var courseUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCourseDetailBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        Utils.setToolbar(this, R.drawable.ic_baseline_cancel_40, "Course Details")

        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility =
            GONE

        binding.rvInstructors.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val course =
            CoursesApiService.createApi().getAllCourseDetails(arguments?.getInt("COURSE_ID"))
        course.enqueue(object : Callback<Courses> {
            override fun onResponse(call: Call<Courses>, response: Response<Courses>) {
                val coursesResponse = response.body()
                courseId = coursesResponse?.id
                courseClass = coursesResponse?._class
                courseTitle = coursesResponse?.title
                courseImage = coursesResponse?.image_480x270
                courseUrl = coursesResponse?.url

                binding.txtCourseTitle.text = courseTitle
                Utils.setImage(requireContext(), courseImage, binding.imgCourseImage)
                binding.txtPrice.text = coursesResponse?.price
                binding.txtClass.text = courseClass
                binding.txtCourseUrl.text =
                    context?.getString(R.string.udemy_base_url, coursesResponse?.url)
                binding.rvInstructors.adapter =
                    InstructorsAdapter(requireContext(), coursesResponse?.visible_instructors!!)
            }

            override fun onFailure(call: Call<Courses>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.course_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val repo = MyCoursesRepo(requireContext())
        if (item.itemId == R.id.takeCourse) {
            doAsync {
                repo.insertCourse(
                    Course(
                        courseId = courseId,
                        _class = courseClass,
                        courseTitle = courseTitle,
                        courseImage = courseImage,
                        courseUrl = courseUrl
                    )
                )
            }
            Utils.showSnackBar("Course Added", view?.findViewById(R.id.course_detail_root_view))
        }
        return super.onOptionsItemSelected(item)
    }
}