package com.example.pokemondisplayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemondisplayapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var ViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        ViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        ViewModel.cancelJob()
    }
}