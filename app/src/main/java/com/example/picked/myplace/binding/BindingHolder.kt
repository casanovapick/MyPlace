package com.example.picked.myplace.binding

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View


open class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val binder: ViewDataBinding = DataBindingUtil.bind(itemView.rootView)

}