package com.exercise.nasapictures.common

import android.content.res.AssetManager
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

/**
 * Hide View
 */
fun View.hideView() {
    visibility = View.GONE
}

fun Date.formatDate(): String {
    return SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(this)
}

/**
 * String date to Date object
 */
fun String.toDate(): Date {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
}

/**
 * @param data is the initial value
 * */
fun <T> MutableLiveData<T>.initWith(data: T): MutableLiveData<T> = this.apply {
    value = data
}

/**
 * open json file from asset
 **/
fun AssetManager.readDataFromAsset(fileName: String): String {
    return open(fileName).bufferedReader().use {
        it.readText()
    }
}

