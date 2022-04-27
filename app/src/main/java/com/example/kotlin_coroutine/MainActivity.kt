package com.example.kotlin_coroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_coroutine.databinding.ActivityMainBinding
import com.example.kotlinmvvm2.viewmodel.MainViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.await
import retrofit2.awaitResponse
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity()
{
    lateinit var binding:ActivityMainBinding
    val TAG="mag2851"
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)



//---------------------------------------------------------------------------------------------------
//        GlobalScope.launch {
//            delay(2000)
//             Log.i(TAG,"this is coroutine ${Thread.currentThread().name}")
//        }
//        Log.i(TAG,"this is thread ${Thread.currentThread().name}")
//---------------------------------------------------------------------------------------------------

//        GlobalScope.launch {
//            val networkCall1=doNetworkCall1()
//            val networkCall2=doNetworkCall2()
//            Log.i(TAG,doNetworkCall1())
//            Log.i(TAG,doNetworkCall2())
//
//        }
//---------------------------------------------------------------------------------------------------



//        GlobalScope.launch(IO) {
//            val result=doNetworkCall1()
//            withContext(Main){
//                binding.tv.text=result
//            }
//        }
//---------------------------------------------------------------------------------------------------



//        logg("before run blocking")
//        runBlocking {
//            launch(IO) {
//                delay(3000)
//                logg("finished IO coroutine1")
//            }
//            launch(IO) {
//                delay(3000)
//                logg("finished IO coroutine2")
//            }
//            logg("start of rung blocking")
//            delay(5000)
//            logg("end run blocking")
//        }
//
//        logg("after run blocking")
//---------------------------------------------------------------------------------------------------


//        val job=GlobalScope.launch(Default){
//             logg("start running calculation.....")
//
//            withTimeout(3000){
//                for (i in 30..40){
//                    if (isActive)
//                        logg("result[${i}]=${fib(i)}")
//                }
//            }
//            logg("end of fibo ...")
//        }
//---------------------------------------------------------------------------------------------------
//

//        runBlocking {
//            delay(2000)
//            job.cancel()
//            logg("canceled job")
//
//        }

//---------------------------------------------------------------------------------------------------
//
//        CoroutineScope(IO).launch {
//            val time= measureTimeMillis {
//                var answer1=async { doNetworkCall1() }
//                val answer2=async { doNetworkCall2() }
//
////            var answer1:String?=null
////            var answer2:String?=null
////            val job1=launch { answer1= doNetworkCall1()}
////            val job2=launch { answer2=doNetworkCall2() }
////            job1.join()
////            job2.join()
//
//                logg("answer1=${answer1.await()}")
//                logg("answer2=${answer2.await()}")
//
//            }
//            logg("time:"+time.toString())
//        }
//---------------------------------------------------------------------------------------------------
//        binding.tv.setOnClickListener{
//            lifecycleScope
//                .launch {
//                while (true){
//                    delay(1000)
//                    logg("still running")
//                }
//            }
//            GlobalScope.launch {
//                delay(5000)
//                val intent=Intent(this@MainActivity,SecondActivity::class.java)
//                    .also {
//                        startActivity(it)
//                        finish()
//                    }
//
//            }
//        }
//----------------------------------------------------------------------------------------------

        GlobalScope.launch(IO){
            val result=viewModel().getBooksWithCoroutine("harry").awaitResponse()
            if (result.isSuccessful)
            logg(result.toString())

        }

//        viewModel().getBooksObserver("harry").observe(this, Observer {
//            if(it==null) Log.i("mag2851-null","mag2851-null-it")
//            else Log.i("mag2851-result",it.toString())
//        })








    }

    fun fib(num:Int):Long{
        if (num==0) return 0
        else if (num==1) return 1
        else return (fib(num-1)+fib(num-2))
    }

    suspend fun doNetworkCall1():String{
        delay(2000)
        return "this is a answer1"
    }

    suspend fun doNetworkCall2():String{
        delay(2000)
        return "this is a answer2"
    }

    private fun logg(str:String){
        Log.i(TAG,str)
    }
    private fun viewModel(): MainViewModel {

        return ViewModelProvider(this).get(MainViewModel::class.java)
    }
}