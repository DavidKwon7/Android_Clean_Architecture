package com.example.common.extensions

fun Any?.isNull() = this == null
fun Any?.isNotNull() = this != isNull()