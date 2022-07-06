package com.sdk.dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdk.dagger2.adapter.UserAdapter
import com.sdk.dagger2.databinding.ActivityMainBinding
import com.sdk.dagger2.model.User
import com.sdk.dagger2.utils.UserResource
import com.sdk.dagger2.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

    @Inject
    lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        userAdapter = UserAdapter()
        setupRv()
        lifecycleScope.launch {
            viewModel.response.collect {
                when (it) {
                    is UserResource.Init -> Unit
                    is UserResource.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.recyclerView.isVisible = false
                    }
                    is UserResource.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                    is UserResource.Success -> {
                        binding.recyclerView.isVisible = true
                        binding.progressBar.isVisible = false
                        userAdapter.submitList(it.users)
                    }
                }
            }
        }
    }

    private fun setupRv() = binding.recyclerView.apply {
        adapter = userAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
        val dividerItemDecoration = DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
        addItemDecoration(dividerItemDecoration)
    }
}