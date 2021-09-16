package com.exercise.nasapictures.ui.nasaphotodetailsscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.exercise.nasapictures.modelclasses.NasaPhotoInfoPojo
import com.exercise.nasapictures.ui.nasaphotodetailsscreen.PhotoDetailsFrag.Companion.fragmentPosition

class PhotosStateAdapter(fragmentActivity: FragmentActivity, private val nasaPhotoList: List<NasaPhotoInfoPojo>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = nasaPhotoList.size

    override fun createFragment(position: Int): Fragment {
        return PhotoDetailsChildFrag().apply {
            arguments = Bundle().apply {
                putInt(fragmentPosition, position)
            }
        }
    }
}