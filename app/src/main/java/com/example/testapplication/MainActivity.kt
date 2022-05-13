package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.Adapter.PhotoListAdapter
import com.example.testapplication.ApiClient.ApiClient
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.domain.PhotoListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    var binding : ActivityMainBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        getData()
    }

    private fun getData() {
        var response : Response<PhotoListResponse> ? = null
        lifecycleScope.launch(Dispatchers.IO){
          response =   ApiClient().getApiClient().
              getPhotoList("27377929-5a627bd02c6dd5858c9d96ded", "yellow+flower","photo").awaitResponse()

            withContext(Dispatchers.Main){
               response?.let { response ->
                    if(response.isSuccessful){
                        Log.d(TAG, "Successfull")
                      var photoListResponse =  response.body()
                       binding?.rvPhotoList?.layoutManager =  LinearLayoutManager(
                          this@MainActivity,
                           LinearLayoutManager.VERTICAL,
                           false
                       )
                        var adapter = PhotoListAdapter()
                        adapter.setData(photoListResponse?.hits)
                        binding?.rvPhotoList?.adapter = adapter
                        binding?.pCircular?.visibility = View.GONE

                    }else{
                        Log.d(TAG, "Api failure with ${response.code()}")
                    }
                }?: kotlin.run {
                   Log.d(TAG, "Error occured")
               }
            }
        }
    }
}