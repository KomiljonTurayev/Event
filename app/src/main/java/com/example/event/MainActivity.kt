package com.example.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.event.databinding.ActivityMainBinding
import com.example.event.service.UserService
import com.example.event.utils.UserResource

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //lifecycle

        //event
        //state

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        binding.apply {

        }
//        val userList = UserService.getUsersList()
//        binding.textView.text = userList.toString()

        myViewModel.getUserList().observe(this, Observer {
            when (it) {
                is UserResource.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is UserResource.Loading -> {
                    Log.d(TAG, "onCreate: Loading")
                }
                is UserResource.Success -> {
                    binding.textView.text = it.list.toString()
                    Log.d(TAG, "onCreate: ${it.list}")
                }
            }
        })
    }
}