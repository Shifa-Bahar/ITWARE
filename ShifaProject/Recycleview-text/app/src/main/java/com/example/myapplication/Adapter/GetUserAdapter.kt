package com.example.myapplication.Adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemBuyAgainBinding
import com.example.myapplication.fragment.CLickFragment2
import com.example.myapplication.fragment.ClickAppoinmentDetails
import com.example.myapplication.model.Moviemodel
import com.example.myapplication.model.Photo

class GetUserAdapter(context: Activity?,private val onClickMovies: ClickAppoinmentDetails) :
    RecyclerView.Adapter<GetUserAdapter.ItemViewHolder>() {
    var arrayList: ArrayList<Moviemodel>? = ArrayList()
    var activity: Activity? = context

    var id: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemBuyAgainBinding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.item_buy_again,
            parent, false
        )
        return ItemViewHolder(binding.root)
    }


    override fun getItemCount(): Int {
        return if (null != arrayList) arrayList!!.size else 0
    }

    class ItemViewHolder internal constructor(itemView:  View) :
        RecyclerView.ViewHolder(itemView) {
        var binding: ItemBuyAgainBinding? = DataBindingUtil.bind(itemView)
    }

    fun setDataChanged(order: ArrayList<Moviemodel>?) {
        arrayList?.clear()
        if (order != null) {
            arrayList?.addAll(order)
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item2 = arrayList!![position]
        holder.binding?.item2 = item2
        holder.binding?.click = onClickMovies
        if(item2.status.equals(1)){
            holder.binding?.isInWishList = false
        }else if(item2.status.equals(2)){
            holder.binding?.isInWishList = true
        }

//        holder.binding?.isInWishList = true
        id = (holder.binding?.item?.id ?: return)
        Log.e("SHIFA", "onBindViewHolder: heloo", )
    }


}