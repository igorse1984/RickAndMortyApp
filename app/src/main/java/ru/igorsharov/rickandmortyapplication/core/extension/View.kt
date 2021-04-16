package ru.igorsharov.rickandmortyapplication.core.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.igorsharov.rickandmortyapplication.R

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadToCircleFromUrl(url: String?) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .apply(RequestOptions().placeholder(R.drawable.ic_eclipse).error(R.drawable.ic_eclipse))
        .apply(RequestOptions().circleCrop())
        .into(this)
}

fun ImageView.loadToSquareFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .into(this)