package com.exercise.nasapictures.ui.nasaphotodetailsscreen

import com.exercise.nasapictures.R
import com.exercise.nasapictures.baseclasses.BaseFrag
import com.exercise.nasapictures.databinding.FragNasaPhotoDetailsChildBinding
import com.exercise.nasapictures.ui.nasaphotodetailsscreen.PhotoDetailsFrag.Companion.fragmentPosition
import com.exercise.nasapictures.viewmodel.PhotosViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoDetailsChildFrag : BaseFrag<FragNasaPhotoDetailsChildBinding, PhotosViewModel>(R.layout.frag_nasa_photo_details_child) {
    override val viewModel: PhotosViewModel by sharedViewModel()

    override fun initialize() {
        super.initialize()
        arguments?.containsKey(fragmentPosition)?.apply {
            arguments?.getInt(fragmentPosition, 0)?.let {
                binding.apply {
                    viewModel?.let { vm ->
                        dataModel = vm.photosList[it]
                    }
                    executePendingBindings()
                }
            }
        }
    }
}