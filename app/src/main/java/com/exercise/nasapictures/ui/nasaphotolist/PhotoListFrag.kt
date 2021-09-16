package com.exercise.nasapictures.ui.nasaphotolist

import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.exercise.nasapictures.R
import com.exercise.nasapictures.baseclasses.BaseFrag
import com.exercise.nasapictures.common.GridItemOffset
import com.exercise.nasapictures.common.ViewClickState
import com.exercise.nasapictures.databinding.FragNasaPhotoListBinding
import com.exercise.nasapictures.viewmodel.PhotosViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoListFrag : BaseFrag<FragNasaPhotoListBinding, PhotosViewModel>(R.layout.frag_nasa_photo_list) {
    override val viewModel: PhotosViewModel by sharedViewModel()

    override fun initialize() {
        super.initialize()
        binding.apply {
            viewModel?.let { vm ->
                val photoListAdapter = NasaPhotoListAdapter(R.layout.list_item_nasa_photo, vm.photosList) { _, position ->
                    run {
                        vm.selectedItem = position
                        vm.viewState.value = ViewClickState.StateNone
                        root.findNavController().navigate(R.id.action_homeFragment_to_photoDetailsFragment)
                    }
                }
                rvPlanetsInfo.apply {
                    adapter = photoListAdapter
                    addItemDecoration(GridItemOffset(this@PhotoListFrag.requireContext(), R.dimen.space_item))
                    (layoutManager as StaggeredGridLayoutManager).gapStrategy =
                        StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                }
            }
        }
    }
}