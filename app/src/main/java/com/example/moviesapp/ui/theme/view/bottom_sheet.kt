package com.example.moviesapp.ui.theme.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
//import androidx.activity.viewModels
import com.example.moviesapp.R
import com.example.moviesapp.ui.theme.viewmodel.MovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetFragment : BottomSheetDialogFragment() {
    private val viewModel: MovieViewModel by  activityViewModels<MovieViewModel>()



    var isRow1Selected = false;
    var isRow2Selected = false;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_layout, container, false)
        val row1 = view?.findViewById<LinearLayout>(R.id.row1)
        val row2 = view?.findViewById<LinearLayout>(R.id.row2)

        val icon1 = view?.findViewById<ImageView>(R.id.icon_1)
        val icon2 = view?.findViewById<ImageView>(R.id.icon_2)

        val text1 = view?.findViewById<TextView>(R.id.text_1)
        val text2 = view?.findViewById<TextView>(R.id.text_2)

        row1?.setOnClickListener {
            viewModel.sortMovieAscending()
            isRow1Selected = !isRow1Selected;
            if (isRow1Selected) {
                icon1?.setColorFilter(resources.getColor(R.color.color_orange, null))
                text1?.setTextColor(resources.getColor(R.color.color_orange, null))


            } else {
                icon1?.setColorFilter(resources.getColor(R.color.color_grey, null))
                text1?.setTextColor(resources.getColor(R.color.color_grey, null))
            }


        }

        row2?.setOnClickListener {
            isRow2Selected = !isRow2Selected;
            viewModel.sortMovieDescending()
            if (isRow2Selected) {

                icon2?.setColorFilter(resources.getColor(R.color.color_orange, null))
                text2?.setTextColor(resources.getColor(R.color.color_orange, null))

            } else {
                icon2?.setColorFilter(resources.getColor(R.color.color_grey, null))
                text2?.setTextColor(resources.getColor(R.color.color_grey, null))
            }

        }

        return view

    }



    // You can override other methods to handle interactions if needed
}