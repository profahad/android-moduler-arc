package com.faaadi.apps.kotlindsl.dogfunfacts.models

import com.google.gson.annotations.SerializedName


data class DogFactsResModel (
    @SerializedName("fact")
    var fact: String? = null
)