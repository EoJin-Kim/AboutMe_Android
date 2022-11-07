package com.ej.aboutme

import android.Manifest
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.data.repository.MemberRepository
import com.ej.aboutme.data.repository.MemberRepositoryImpl
import com.ej.aboutme.databinding.ActivityMainBinding
import com.ej.aboutme.fragment.group.GroupMemberFragment
import com.ej.aboutme.fragment.group.OpenGroupFragment
import com.ej.aboutme.fragment.member.LoginFragment
import com.ej.aboutme.fragment.member.MemberHomeFragment
import com.ej.aboutme.fragment.member.SignupFragment
import com.ej.aboutme.fragment.group.MyGroupFragment
import com.ej.aboutme.fragment.member.MemberHomeEditFragment
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.util.MainViewModelFactory
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    val repository : MemberRepository by lazy{ MemberRepositoryImpl(application)}
    val viewModelFactory : MainViewModelFactory by lazy{ MainViewModelFactory(repository)}
    val mainViewModel : MainViewModel by lazy { ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java) }
    val queryPreferences = QueryPreferences()

    val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        SystemClock.sleep(0)
        requestPermissions(permissionList,0)

        setTheme(R.style.Theme_AboutMe)


        binding = ActivityMainBinding.inflate(layoutInflater)


        binding.navigation.background = null
        this.window?.apply {
            this.statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        }

        val naviListener = object : NavigationBarView.OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.menu_my_info -> {
                        setFragment("my_home")
                    }
                    R.id.menu_my_group ->{
                        setFragment("my_group")
                    }
                }
                return true
            }
        }

        binding.navigation.setOnItemSelectedListener(naviListener)



        setContentView(binding.root)

        if(queryPreferences.getLoginCheck(applicationContext) != "none"){
//            setFragment("login")
            setFragment("my_home")
        }
        else{
            setFragment("login")
        }



    }

    fun setFragment(name : String){
        var tran = supportFragmentManager.beginTransaction()

        when(name){
            "login" ->{
                tran.addToBackStack(name)
                tran.replace(R.id.container, LoginFragment.newInstance())
            }
            "signup" ->{
                tran.addToBackStack(name)
                tran.replace(R.id.container, SignupFragment.newInstance())
            }
            "my_home" -> {
                tran.replace(R.id.container, MemberHomeFragment.newInstance())
            }

            "my_home_edit" -> {
                tran.replace(R.id.container, MemberHomeEditFragment.newInstance())
            }

            "my_group" -> {
                tran.addToBackStack(name)
                tran.replace(R.id.container, MyGroupFragment.newInstance())
            }
            "enter_group" ->{
                Log.d("Moon","A")
                tran.addToBackStack(name)
//                tran.add(R.id.container,OpenGroupFragment.newInstance())
                tran.replace(R.id.container,OpenGroupFragment.newInstance())

            }
            "open_member" ->{
                tran.addToBackStack(name)
                tran.replace(R.id.container,GroupMemberFragment())
            }

        }

        tran.commit()
    }


}