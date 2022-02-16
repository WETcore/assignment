package com.aqua.snapask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aqua.snapask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            val adapter = UserAdapter()
            recycler.adapter = adapter

            viewModel.users.observe(this@MainActivity) {
                adapter.submitList(it)
            }
        }
    }
}