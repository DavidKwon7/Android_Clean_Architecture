package com.example.common.extensions

import java.lang.Exception

sealed class Resource<out T> {
    class Success<T>(val data: T): Resource<T>()
    class Error(val exception: Exception): Resource<Nothing>()
    object Loading: Resource<Nothing>()
    object Empty: Resource<Nothing>()
}
