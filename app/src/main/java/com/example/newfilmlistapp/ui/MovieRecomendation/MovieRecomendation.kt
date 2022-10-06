package com.example.newfilmlistapp.ui.MovieRecomendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.newfilmlistapp.databinding.FragmentMovieRecomendationBinding
import com.example.newfilmlistapp.view_model.ViewModel_SortByDate
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.model.MovieWrapper


class MovieRecomendation : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentMovieRecomendationBinding
    private val viewModel: ViewModel_SortByDate by viewModels()
    private lateinit var movieWrapper: MovieWrapper

    private var positionGenre = 0
    private var positionYear = 0

    // for navigation



//    private var movie_ID: Int = 0
//    private lateinit var poster_path: String
//    private var vote_average: Float = 0.0F




    // Save Date

//    private var year: Int = 0
//    private var genre: Int = 0
//    private var poster_path: String = ""
//    private var tv_below_poster: String = ""



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieRecomendationBinding.inflate(inflater, container, false)

        bindGenres()
        setupYears()
        setListenerButtonRequest()
        toMovieDetail()

        return binding.root
    }


    override fun onPause() {
        super.onPause()
    }




    private fun bindGenres() {

        viewModel.genres.observe(viewLifecycleOwner)  {
                setupGenres(it.genres)
        }


    }


    private fun bindYears() {

        viewModel.movie.observe(viewLifecycleOwner) {

        }

    }





    private fun setupGenres(genres: List<Genres>) {

        val listPopupWindowButton = binding.btnPopupMenuGenre
        val listPopupWindow = ListPopupWindow(this.requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)


        // Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton


        val arrayAdapterGenre = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            genres.map { it.name }
        )
        listPopupWindow.setAdapter(arrayAdapterGenre)

        // Set list popup's item click listener
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            positionGenre = position

            // Dismiss popup.
            listPopupWindow.dismiss()
        }

        // Show list popup window on button click.
        listPopupWindowButton.setOnClickListener { v: View? -> listPopupWindow.show() }


    }

    private fun setupYears() {

        val listPopupWindowButton = binding.btnPopupMenuYear
        val listPopupWindow = ListPopupWindow(this.requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)


        // Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton

        val listYear = mutableListOf<Int>()
        for (i in 2022..1874) {
            listYear.add(i)
        }
        val arrayAdapterYear = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listYear
        )

        listPopupWindow.setAdapter(arrayAdapterYear)

        // Set list popup's item click listener
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            positionYear = position

            // Dismiss popup.
            listPopupWindow.dismiss()
        }

        // Show list popup window on button click.
        listPopupWindowButton.setOnClickListener { v: View? -> listPopupWindow.show() }



    }


    private fun setListenerButtonRequest() {

        binding.request.setOnClickListener {

            viewModel.requestMovie(positionYear,positionGenre)



        }

    }


    fun toMovieDetail() {

        binding.pictureFilm.setOnClickListener {

            val action = MovieRecomendationDirections.actionRandomToMovieDetail(movie_ID,poster_path,vote_average)
            it.findNavController().navigate(action)

        }

    }


}




