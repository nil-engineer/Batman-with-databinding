package com.test.batman.model


import java.io.Serializable


data class SearchItemModel(
    var Title: String,
    var Year: String,
    var imdbID: String,
    var Type: String,
    var Poster: String
) : Serializable