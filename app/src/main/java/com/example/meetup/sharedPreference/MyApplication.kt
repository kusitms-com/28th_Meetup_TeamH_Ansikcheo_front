package com.example.meetup.sharedPreference

import android.app.Application
import com.example.meetup.BuildConfig
import com.example.meetup.model.CartFood
import com.example.meetup.model.request.AddressesRequestModel
import com.example.meetup.model.request.SignUpRequestModel
import com.kakao.sdk.common.KakaoSdk

class MyApplication : Application() {
    companion object{
        lateinit var preferences: PreferenceUtil

        var userName = ""

        var category = ""
        var categoryId = 0

        var filtering = "추천순"

        var foodId = 0

        var regionId = 0

        var mypageSeller = false

        var address = AddressesRequestModel(0, 0, "", "", "", "")

        var cartItem = mutableListOf<CartFood>()
    }

    override fun onCreate() {
        preferences = PreferenceUtil(applicationContext)
        super.onCreate()

        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)

    }
}