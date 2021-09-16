package com.exercise.nasapictures.common

sealed class ViewClickState {
    object ButtonPreviousClick : ViewClickState()
    object ButtonNextClick : ViewClickState()
    object ImageViewBackClick : ViewClickState()
    object StateNone : ViewClickState()
}