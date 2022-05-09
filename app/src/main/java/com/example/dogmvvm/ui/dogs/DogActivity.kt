package com.example.dogmvvm.ui.dogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dogmvvm.R
import com.example.dogmvvm.utilities.InjectorUtils
import androidx.lifecycle.ViewModelProviders
import com.example.dogmvvm.databinding.ActivityDogBinding

class DogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUi()


    }

    fun initializeUi(){
        val factory = InjectorUtils.provideDogViewModelFactory()
        val viewModel = ViewModelProviders.of(this,factory)
            .get(DogViewModel::class.java)

        viewModel.getDogs().observe(this, Observer {dogs->
            binding.textView.text ="Fetched dogs"
        })
        binding.btnAllBreed.setOnClickListener{
            viewModel.fetchDogs()
            binding.textView.setText("Cleared data")
        }

    }
}