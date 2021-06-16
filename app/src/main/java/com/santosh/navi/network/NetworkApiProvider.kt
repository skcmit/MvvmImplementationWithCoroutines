package com.santosh.navi.network

object NetworkApiProvider {
    private val networkApiInterfaceImpl by lazy {
        NetworkApiInterfaceImpl(RetrofitProvider.retrofitInstance)
    }

    fun getNetworkApi(): NetworkApiInterface = networkApiInterfaceImpl
}