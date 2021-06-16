package com.santosh.navi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.santosh.navi.databinding.MainActivityBinding
import com.santosh.navi.ui.pull_requests.PullRequestListingFragment
import com.santosh.navi.util.FragmentNavigationHandler
import com.santosh.navi.util.addFragment

class MainActivity : AppCompatActivity(), FragmentNavigationHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainActivityBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(PullRequestListingFragment.getInstance())
    }

    override fun switchFragment(fragment: Fragment) {
        addFragment(fragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount < 1) finish()
    }
}