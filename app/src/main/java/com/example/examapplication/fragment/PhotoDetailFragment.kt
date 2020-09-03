package com.example.examapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.examapplication.R
import com.example.examapplication.model.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_picture_detail.*


class PhotoDetailFragment : Fragment() {

    private var photo: Photo? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        photo = arguments?.getParcelable("PHOTO_MODEL") as? Photo
        activity!!.title = photo?.title
        Picasso.get().load(photo?.url).into(detailImageView)
    }
}