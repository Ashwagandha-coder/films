package com.example.newfilmlistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newfilmlistapp.databinding.FragmentPopularBinding


class Popular : Fragment() {

    private lateinit var binding: FragmentPopularBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPopularBinding.inflate(inflater,container,false)

        return binding.root
    }







}