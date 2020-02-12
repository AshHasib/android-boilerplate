package com.ashhasib.android_boilerplate.picasso;

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.squareup.picasso.Picasso

public class PicassoLoad {


    fun loadImage(
        imgUrl:String,
        imgView:ImageView,
        circularProgressDrawable: CircularProgressDrawable
    ) {

        /**
         * Picasso image load tutorial
         */
        Picasso.get()
            .load(imgUrl)
            .placeholder(circularProgressDrawable)
            .into(imgView)
    }
}
