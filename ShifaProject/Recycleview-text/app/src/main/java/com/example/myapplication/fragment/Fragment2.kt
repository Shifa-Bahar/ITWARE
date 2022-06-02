package com.example.myapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Adapter.GetUserAdapter
import com.example.myapplication.Adapter.WishListAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.Fragment1Binding
import com.example.myapplication.databinding.Fragment2Binding
import com.example.myapplication.model.MovieRequestModel
import com.example.myapplication.model.MovieWishlistRequestModel
import com.example.myapplication.network.RequestResult
import com.example.myapplication.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Fragment2 : Fragment(), CLickFragment2 ,ClickAppoinmentDetails{
    var str : String = "log info"
    private val viewModel: UserViewModel by activityViewModels()
    lateinit var binding: Fragment2Binding
    lateinit var adapter:WishListAdapter
    lateinit var adapteruser:GetUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.fragment1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_2, container, false
        )
        iniUI()
        return binding.root
    }
    fun iniUI(){
        binding.click = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.textObserver = viewModel.textObservable
//        viewModel.textObservable.setEditText(binding.name)
        iniRV()
        getUserDetails()
    }
    fun iniRV(){
        adapter = WishListAdapter(requireActivity())
        binding.rvItems.adapter = adapter

        adapteruser = GetUserAdapter(requireActivity(),this)
        binding.rvItems.adapter = adapteruser
    }
    override fun getUserDetails(){
        val req = MovieRequestModel("${viewModel.textObservable.getValue()}")
        viewModel.getValues(req).observe(viewLifecycleOwner,{
            it?.let { it ->
                when(it.status){
                    RequestResult.Status.SUCCESS->{
                        viewModel.loading.value = false
                        viewModel.text.value = "Sucess"
                        it.data?.let {
                            adapteruser.setDataChanged(it.data)
                        }

//                            viewModel.textObservable.setValue(requireContext(),it.data)
//                            Toast.makeText(requireContext(),it.data,)
//                            Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
                    }
                    RequestResult.Status.ERROR ->{
                        viewModel.loading.value = false
                        viewModel.text.value = "Error"
                        viewModel.textObservable.setValue("Error")
                    }
                    RequestResult.Status.LOADING->{
                        viewModel.loading.value = true
                    }
                }
            }
        })

    }
    override fun getMovieWishlist(id: Int){
        var req = MovieWishlistRequestModel("${viewModel.textObservable.getValue()}",id)
        viewModel.getMovieWishlist(req).observe(viewLifecycleOwner,{
            it?.let { it ->
                when(it.status){
                    RequestResult.Status.SUCCESS->{
                        viewModel.loading.value = false
                        viewModel.text.value = "Sucess"
                        if(!(it.data?.message.isNullOrEmpty())){
                            viewModel.wishlistselected = it.data?.message?.contains("Added") == true
                        }
                        Toast.makeText(context, "${it.data?.message}", Toast.LENGTH_LONG).show()
                        getUserDetails()
//                        it.data?.let {
//                            adapteruser.setDataChanged(it.data)
//                        }

//                            viewModel.textObservable.setValue(requireContext(),it.data)
//                            Toast.makeText(requireContext(),it.data,)
//                            Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
                    }
                    RequestResult.Status.ERROR ->{
                        viewModel.loading.value = false
                        viewModel.text.value = "Error"
                        viewModel.textObservable.setValue("Error")
                    }
                    RequestResult.Status.LOADING->{
                        viewModel.loading.value = true
                    }
                }
            }
        })

    }

    override fun getLogin() {

    }

    override fun onClickMovie(id: Int) {
        Log.e("SHIFA", "onClickMovie: $id")

    }

    override fun onClickGotoFragment1() {
        findNavController().navigateUp()
    }

    override fun onClickAppoinmentDetails(id: Int) {
        viewModel.wishlistid = id
        Log.e("SHIFA", "onClickAppoinmentDetails: $id")
        getMovieWishlist(id)

    }
}