package com.jay.pinkoo.ui.dashboard

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jay.pinkoo.R
import com.jay.pinkoo.databinding.ActivityMainBinding
import com.jay.pinkoo.di.viewmodel.CategoryViewModel
import com.jay.pinkoo.di.viewmodel.UserListViewModel
import com.jay.pinkoo.ui.adapter.CategoryAdapter
import com.jay.pinkoo.ui.adapter.GridAdapter
import com.jay.pinkoo.ui.adapter.VerticalAadpter
import com.jay.pinkoo.util.showConfirmationDialog

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var verticalAadpter: VerticalAadpter
    lateinit var gridAdapter: GridAdapter
    lateinit var viewModel: CategoryViewModel
    lateinit var productViewModel: UserListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        productViewModel = ViewModelProvider(this)[UserListViewModel::class.java]

        binding.rvVrList.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onResume() {
        super.onResume()
        getCategory()
        getUserList()
        clickListener()

    }

    private fun clickListener() {
        binding.ivList.setOnClickListener {
            changeIconOnClick(binding.ivList)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getUserList() {
        productViewModel.userList()

        productViewModel._userResponseLiveData.observe(this) { userResponse ->
            if (userResponse != null) {
                Log.d("tocken", userResponse.toString())
                val total = userResponse.total
                binding.tvTotal.text = "$total Product Found"
                gridAdapter = GridAdapter(this, userResponse.data)
                binding.rvVrList.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvVrList.adapter=gridAdapter
                verticalAadpter = VerticalAadpter(this, userResponse.data)

            }
        }
        productViewModel._errorMessageLiveData.observe(this) { errorMessage ->
            if (errorMessage != null) {
                showConfirmationDialog(getString(R.string.error), errorMessage)
            }
        }
    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getCategory() {
        viewModel.userList()

        viewModel._userResponseLiveData.observe(this) { userResponse ->
            if (userResponse != null) {
                Log.d("tocken", userResponse.toString())
                categoryAdapter = CategoryAdapter(userResponse.data)
                binding.rvHzCategory.adapter = categoryAdapter
                binding.rvHzCategory.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            }
        }
        viewModel._errorMessageLiveData.observe(this) { errorMessage ->
            if (errorMessage != null) {
                showConfirmationDialog(getString(R.string.error), errorMessage)
            }
        }

    }

    fun changeIconOnClick(imageView: ImageView) {
        if (imageView.drawable.constantState == resources.getDrawable(R.drawable.ic_list).constantState) {
            imageView.setImageResource(R.drawable.ic_grid)
            binding.rvVrList.layoutManager = LinearLayoutManager(this)
            binding.rvVrList.adapter = verticalAadpter
        } else {
            imageView.setImageResource(R.drawable.ic_list)
            binding.rvVrList.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rvVrList.adapter = gridAdapter
        }
    }
}