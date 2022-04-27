package com.example.kotlin_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.kotlin_coroutine.databinding.ActivityMainBinding
import com.example.kotlin_coroutine.databinding.MitchActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MitchExampleActivity : AppCompatActivity()
{
    lateinit var binding:MitchActivityBinding
    val RESULT1="Result1"
    val RESULT2="Result2"
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

         binding=DataBindingUtil.setContentView(this,R.layout.mitch_activity)

        binding.btn.setOnClickListener{
            //IO => input,output of i guess poolThread,Main=>main thread,Default=>for heavy request
            CoroutineScope(IO).launch {
                val result1=getResult1FromServer()
                showStringOnTextView("result1")
                Log.i("mag2851-result1",result1)

                showStringOnTextView(getResult2FromServer())
            }




        }
    }
    private suspend fun getResult1FromServer():String
    {
        logThread("getResult1FromServer")
        delay(1000)
         return RESULT1
    }
    private suspend fun getResult2FromServer():String
    {
        logThread("getResult1FromServer")
        delay(1000)
        return RESULT2
    }
    private suspend fun showStringOnTextView(input:String){
        withContext(Main){
            binding.tv.text=input
        }

    }
    private fun logThread(methodName:String){
        Log.i("mag2851->","${methodName}:${Thread.currentThread().name}")
    }






}