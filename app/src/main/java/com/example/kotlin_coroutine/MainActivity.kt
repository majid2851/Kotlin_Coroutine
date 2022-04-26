package com.example.kotlin_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.kotlin_coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity()
{
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btn.setOnClickListener{



        }
    }
    private suspend fun getResult1FromServer():String
    {
        logThread("getResult1FromServer")
        delay(1000)
         return "Result#1"
    }

    private suspend fun getResult2FromServer(){

    }
    private fun logThread(methodName:String){
        Log.i("mag2851->","${methodName}:${Thread.currentThread().name}")
    }






}