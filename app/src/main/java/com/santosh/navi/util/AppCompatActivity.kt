package com.santosh.navi.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.santosh.navi.R

fun AppCompatActivity.addFragment(targetFragment: Fragment) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.add(R.id.container, targetFragment)
    transaction.addToBackStack(null)
    transaction.commit()
}