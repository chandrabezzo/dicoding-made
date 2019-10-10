package com.bezzo.core.base

sealed class ViewModelState

object Loading: ViewModelState()
object Empty : ViewModelState()
data class Receive<M>(val data: M): ViewModelState()
data class Error(val message: String): ViewModelState()