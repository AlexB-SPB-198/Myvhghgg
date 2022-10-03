package com.example.myapplication11

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Preference(private val context: Context) {
    private  var preference: SharedPreferences =
        context.getSharedPreferences("simple_data",MODE_PRIVATE)


    fun getIsShowBoarding():Boolean{
        return preference.getBoolean("is_show",false)
    }
    fun showBoarding(isShow:Boolean){
        preference.edit().putBoolean("is_show",true).apply()
    }

    fun setProfileImage(url:String){
        preference.edit().putString("profile_image",url).apply()
    }
    fun getProfileImage():String = preference.getString("profile_image","").toString()

    fun setName(name :String){
        preference.edit().putString("profile_name",name).apply()
    }
    fun getName():String= preference.getString("profile_name","").toString()

    fun setLastName(name :String){
        preference.edit().putString("profile_lastname",name).apply()
    }
    fun getLastName():String= preference.getString("profile_lastname","").toString()

    fun setAge(name :String){
        preference.edit().putString("profile_name",name).apply()
    }
    fun getAge():String= preference.getString("profile_name","").toString()
    fun showBoarding() {
        TODO("Not yet implemented")
    }


}