package com.google.rickandmorty.characters

data class Info(
    val count: Int = 0,
    val next: String?,
    val pages: Int = 0,
    val prev: String?
)