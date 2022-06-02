package com.example.myapplication.fragment

import android.os.Bundle
import android.text.BoringLayout.make
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Adapter.GetUserAdapter
import com.example.myapplication.Adapter.WishListAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.Fragment1Binding
import com.example.myapplication.model.*
import com.example.myapplication.network.RequestResult
import com.example.myapplication.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar.make
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Fragment1 : Fragment(),ClickFragment1 {
    lateinit var binding: Fragment1Binding
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_1, container, false
        )
        iniUI()
        return binding.root
    }
    fun iniUI(){
        binding.click = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.textObserver = viewModel.textObservable
        binding.textObserverpas = viewModel.textObserverpas
        viewModel.textObservable.setEditText(binding.name)
        viewModel.textObserverpas.setEditText(binding.password)
    }
    override fun getLogin(){
        val req = LoginRequestModel("${viewModel.textObservable.getValue()}","${viewModel.textObserverpas.getValue()}")
            viewModel.getLogin(req).observe(this,{
                it?.let { it ->
                    when(it.status){
                        RequestResult.Status.SUCCESS->{
                            viewModel.loading.value = false
//                            viewModel.text.value = "Sucess"
                            findNavController().navigate(R.id.actionFragment1TOFragment2)
                        }
                        RequestResult.Status.ERROR ->{
                            viewModel.loading.value = false
//                            viewModel.text.value = "ERROR"
                        }
                        RequestResult.Status.LOADING->{
                            viewModel.loading.value = true
                        }
                    }
                }
            })

    }


}