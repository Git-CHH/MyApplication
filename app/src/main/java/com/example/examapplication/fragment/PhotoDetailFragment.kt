package com.example.examapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.examapplication.R
import com.example.examapplication.model.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_picture_detail.*


class PhotoDetailFragment : Fragment() {

    private var photo: Photo? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        photo = arguments?.getParcelable("PHOTO_MODEL") as? Photo
        activity!!.title = photo?.title
        Picasso.get().load(photo?.url).into(detailImageView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity!!.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}