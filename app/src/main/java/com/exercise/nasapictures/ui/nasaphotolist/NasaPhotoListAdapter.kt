package com.exercise.nasapictures.ui.nasaphotolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.exercise.nasapictures.modelclasses.NasaPhotoInfoPojo
import com.exercise.nasapictures.BR

class NasaPhotoListAdapter(@LayoutRes val listItemLayout: Int, list: List<NasaPhotoInfoPojo>, private val listener: (NasaPhotoInfoPojo, Int) -> Unit) : RecyclerView.Adapter<NasaPhotoListAdapter.PhotoViewHolder>() {
    private val photosList = list

    inner class PhotoViewHolder(private val viewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root), View.OnClickListener {
        fun bind(dataModel: NasaPhotoInfoPojo, position: Int) {
            viewBinding.setVariable(BR.dataModel, dataModel)
            viewBinding.setVariable(BR.clickHandler, this)
            viewBinding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            listener.invoke(photosList[adapterPosition], adapterPosition)
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photosList[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                listItemLayout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = photosList.size
}