package com.faaadi.apps.kotlindsl.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faaadi.apps.kotlindsl.R
import com.faaadi.apps.kotlindsl.dogfunfacts.ui.DogFunFactsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FragmentContainer, DogFunFactsFragment())
            .commit()
    }
}