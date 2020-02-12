package com.ashhasib.android_boilerplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashhasib.android_boilerplate.firebase.FireabaseIO
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //FireabaseIO(this).writeData()
        FireabaseIO(this).readData()


    }
}
