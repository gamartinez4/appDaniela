package com.example.borrar

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.appdaniela.R


@BindingAdapter("android:loadImage")
fun imageUrlLoad(imageView: ImageView,url:String){

    imageView.setBackgroundResource(0)
    Glide.with(imageView).load(url).into(imageView)
}
