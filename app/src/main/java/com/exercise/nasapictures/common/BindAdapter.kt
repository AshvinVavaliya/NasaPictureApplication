package com.exercise.nasapictures.common

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exercise.nasapictures.R
import androidx.databinding.BindingAdapter

class BindAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("desc")
        fun setDescription(textView: AppCompatTextView, desc: String?) {
            textView.apply {
                desc?.let {
                    text = desc
                } ?: kotlin.run {
                    hideView()
                }
            }
        }

        @JvmStatic
        @BindingAdapter("imagePathUrl")
        fun bindImage(imageView: AppCompatImageView, url: String?) {
            url?.let {
                Glide.with(imageView.context)
                    .load(it)
                    .apply(RequestOptions.noTransformation())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)
            } ?: kotlin.run {
                Glide.with(imageView.context)
                    .load(R.drawable.ic_launcher_background)
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("dateformat")
        fun formatDate(textView: AppCompatTextView, dateValue: String?) {
            textView.apply {
                dateValue?.let {
                    text = it.toDate()?.formatDate()
                } ?: kotlin.run {
                    hideView()
                }
            }
        }

        @JvmStatic
        @BindingAdapter("copyright")
        fun formatCopyrightValue(textView: AppCompatTextView, name: String?) {
            textView.apply {
                name?.let {
                    text = textView.context.getString(R.string.copyright, name)
                } ?: kotlin.run {
                    hideView()
                }
            }
        }
    }
}