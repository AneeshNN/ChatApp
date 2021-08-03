package com.example.chatsdk.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chatsdk.R
import com.example.chatsdk.base.ui.BaseActivity
import com.example.chatsdk.databinding.ActivityHomeBinding
import com.example.chatsdk.ui.view_model.HomeViewModel
import com.example.chatsdklib.interfaces.SDKCallback
import com.example.chatsdklib.ui.fragments.ListUserFragment

class MainActivity : BaseActivity(), SDKCallback {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    private lateinit var listUserFragment: ListUserFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this

        listUserFragment = ListUserFragment.newInstance(true, this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_frame_layout, listUserFragment).commit()
    }

    override fun onUserDataLoaded(onClickUser: String) {
        showToast("Toast from user application, onClick user is - $onClickUser")
    }
}