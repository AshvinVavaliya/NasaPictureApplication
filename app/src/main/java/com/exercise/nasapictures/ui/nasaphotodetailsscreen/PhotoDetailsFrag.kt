package com.exercise.nasapictures.ui.nasaphotodetailsscreen

import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.exercise.nasapictures.R
import com.exercise.nasapictures.baseclasses.BaseFrag
import com.exercise.nasapictures.common.ViewClickState
import com.exercise.nasapictures.databinding.FragNasaPhotoDetailsParentBinding
import com.exercise.nasapictures.viewmodel.PhotosViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoDetailsFrag : BaseFrag<FragNasaPhotoDetailsParentBinding, PhotosViewModel>(R.layout.frag_nasa_photo_details_parent) {
    override val viewModel: PhotosViewModel by sharedViewModel()

    override fun initialize() {
        super.initialize()
        setPagerAdapter()
        observeViewClick()
        bindViewPagerListener()
    }

    private fun setPagerAdapter() {
        binding.apply {
            viewModel?.let { vm ->
                vpPhotos.adapter = PhotosStateAdapter(this@PhotoDetailsFrag.requireActivity(), vm.photosList)
            }
            vpPhotos.post {
                viewModel?.let { vm ->
                    vpPhotos.setCurrentItem(vm.selectedItem, false)
                }
            }
        }
    }

    private fun bindViewPagerListener() {
        binding.vpPhotos.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.btnPrevious.isEnabled = position != 0
                    binding.btnNext.isEnabled = position != viewModel.photosList.size - 1
                }
            })
        }
    }

    private fun setViewPagerItem(case: Int) {
        binding.vpPhotos.apply {
            if (case == 0) {
                if (currentItem < viewModel.photosList.size) {
                    currentItem += 1
                }
            } else {
                if (currentItem >= 0) {
                    currentItem -= 1
                }
            }
        }
    }

    companion object {
        const val fragmentPosition: String = "fragmentPosition"
    }

    private fun observeViewClick() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                when (it) {
                    is ViewClickState.ImageViewBackClick -> {
                        binding.root.findNavController().popBackStack()
                    }
                    is ViewClickState.ButtonPreviousClick -> {
                        setViewPagerItem(1)
                    }
                    is ViewClickState.ButtonNextClick -> {
                        setViewPagerItem(0)
                    }
                    else -> {
                        //do nothing
                    }
                }
                viewModel.viewState.value = ViewClickState.StateNone // Reset status
            }
        }
    }
}