package com.example.examapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examapplication.Outcome
import com.example.examapplication.viewmodel.PhotoViewModel
import com.example.examapplication.R
import com.example.examapplication.adapter.PhotoAdapter
import com.example.examapplication.model.Photo
import kotlinx.android.synthetic.main.fragment_picture.*
import org.koin.android.viewmodel.ext.android.viewModel

class PhotoFragment : Fragment() {

    private val photoViewModel: PhotoViewModel by viewModel()
    private var photoList = mutableListOf<Photo>()
    private lateinit var mAdapter: PhotoAdapter

    companion object {
        fun newInstance() = PhotoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.title = "Photos"
        initViewModel()
        photoViewModel.getListPhoto()

        pictureRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            mAdapter = PhotoAdapter { photo: Photo -> onItemClick(photo)}
            pictureRecyclerView.adapter = mAdapter
            onFlingListener = null
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    private fun onItemClick(photo: Photo) {
        var action = PhotoFragmentDirections.actionPictureFragmentToPictureDetailFragment(photo)
        findNavController().navigate(action)
    }

    private fun initViewModel(){
        photoViewModel.photoListLiveData.observe(this, Observer {
            when (it) {
                is Outcome.Success -> {
                    if (it.data.isNotEmpty()) {
                        photoList = it.data as MutableList<Photo>
                        mAdapter.submitList(photoList)
                        mAdapter.notifyDataSetChanged()
                    }
                }

                is Outcome.Failure -> {
                    Log.d("Failure",":"+ it.e.toString())
                }

                is Outcome.Progress -> {

                }
            }
        })
    }

}