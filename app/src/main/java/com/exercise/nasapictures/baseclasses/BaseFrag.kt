package com.exercise.nasapictures.baseclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.exercise.nasapictures.BR

abstract class BaseFrag<Binding : ViewDataBinding, ViewModel : BaseViewModel>(@LayoutRes val layoutRes: Int) : Fragment() {
    protected lateinit var binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    private fun bindingViewModel() {
        binding.apply {
            lifecycleOwner = this@BaseFrag
            setVariable(BR.viewModel, viewModel)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingViewModel()
        initialize()
    }

    open fun initialize() {}
}