package com.example.picked.myplace.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.example.picked.myplace.R
import com.example.picked.myplace.glide.GlideApp

@BindingAdapter("bind:imageUrl")
fun setImageUrl(imageView: ImageView?, imageUrl: String) {
    imageView?.let {
        GlideApp.with(imageView).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView)
    }
}