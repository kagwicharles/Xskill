package com.example.co_leaner.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.co_leaner.R
import com.google.android.material.snackbar.Snackbar

class Utils {

    companion object {
        fun setToolbar(
            fragment: Fragment,
            drawableResource: Int? = R.drawable.ic_baseline_school_40,
            title: String? = "Xskill",
            color: String = "#0277bd"
        ) {
            val toolbar = (fragment.activity as AppCompatActivity).supportActionBar
            toolbar?.title = title
            val drawable =
                fragment.context?.let {
                    AppCompatResources.getDrawable(
                        it,
                        drawableResource!!
                    )
                }
            drawable?.setTint(Color.parseColor(color))
            toolbar?.setHomeAsUpIndicator(
                drawable
            )
        }

        fun showSnackBar(message: String, view: View?) {
            Snackbar.make(view!!, message, Snackbar.LENGTH_LONG).show()
        }

        fun setImage(context: Context, url: String?, imageView: ImageView) {
            Glide
                .with(context)
                .load(url)
                .into(imageView)
        }

        fun openWebPage(context: Context, uri: String) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(context.getString(R.string.udemy_base_url, uri))
                )
            )
        }

        fun openFragment(fragment: Fragment, bundle: Bundle, destination: Int) {
            val navHostFragment =
                fragment.activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(destination, bundle)
        }
    }
}