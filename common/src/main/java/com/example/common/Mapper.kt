package com.example.common

interface Mapper<I, O> {

    fun from(i: I?): O

    fun to(o: O?): I

    fun fromList(list: List<I>?): List<O> {
        return list?.mapNotNull { from(it) } ?: listOf()
    }

    fun toLIst(list: List<O>?): List<I> {
        return list?.mapNotNull { to(it) } ?: listOf()

    }
}