package com.exercise.nasapictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.exercise.nasapictures.databinding.ActivityMainBinding
import com.exercise.nasapictures.viewmodel.PhotosViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModelPhotos: PhotosViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)?.apply {
            setVariable(BR.viewModel, viewModelPhotos)
            executePendingBindings()
        }
    }
}