package com.example.dogmvvm.ui.dogs

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dogmvvm.R
import com.example.dogmvvm.data.api.ApiResponse
import com.example.dogmvvm.databinding.ActivityDogBinding
import com.example.dogmvvm.ui.RecycleView.DogAdapter
import com.example.dogmvvm.utilities.InjectorUtils


class DogActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDogBinding
    private lateinit var dogAdapter: DogAdapter
    private lateinit var viewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUi()
        setupRecyclerview()

        val breedTypes = resources.getStringArray(R.array.BreedType)

        val adapter = ArrayAdapter(this,R.layout.spinner_item,R.id.spinnerTv,breedTypes)

        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener= object :
            AdapterView.OnItemSelectedListener {


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@DogActivity,getString(R.string.selected_item)+breedTypes[position],Toast.LENGTH_LONG).show()
                binding.progessBar.isVisible=true
                viewModel.fetchByBreed(breedTypes[position])

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }



        }


    }

    private fun initializeUi(){
        val factory = InjectorUtils.provideDogViewModelFactory()
         viewModel = ViewModelProviders.of(this,factory)
            .get(DogViewModel::class.java)

        viewModel.getDogs().observe(this, Observer {dogResponse->


          if(dogResponse is ApiResponse.Success){

              var stringBuilder =StringBuilder()
              dogAdapter.images=dogResponse.data!!.message
              dogResponse.data.message.forEach{ e->
                  stringBuilder.append(e+"\n\n")

              }
              //binding.textView.text =stringBuilder
          }else if(dogResponse is ApiResponse.Error){
             // binding.textView.text ="Something went wrong ${dogResponse.exception}"
          }
            binding.progessBar.isVisible=false

        })
        binding.btnAllBreed.setOnClickListener{
            binding.progessBar.isVisible=true
            viewModel.fetchDogs()
          //  binding.textView.setText("Loading data")
        }

    }

    private fun setupRecyclerview()= binding.rvTodos.apply {
        dogAdapter = DogAdapter()
        adapter= dogAdapter

        //layoutManager= LinearLayoutManager(this@DogActivity)
        layoutManager = GridLayoutManager(this@DogActivity, 2)


    }
}