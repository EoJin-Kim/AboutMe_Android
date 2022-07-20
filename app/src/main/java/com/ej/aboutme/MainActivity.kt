package com.ej.aboutme

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.data.MainViewModel
import com.ej.aboutme.data.repository.MemberRepositoryImpl
import com.ej.aboutme.databinding.ActivityMainBinding
import com.ej.aboutme.fragment.navi.MyGroupFragment
import com.ej.aboutme.fragment.navi.MyHomeFragment
import com.ej.aboutme.util.MainViewModelFactory
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {


    val myHomeFragment = MyHomeFragment()
    val myGroupFragment = MyGroupFragment()



    lateinit var binding : ActivityMainBinding
    lateinit var mainViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        SystemClock.sleep(0)

        setTheme(R.style.Theme_AboutMe)


        binding = ActivityMainBinding.inflate(layoutInflater)

        var repository = MemberRepositoryImpl(application)
        var viewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)


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

        setFragment("my_home")


    }

    fun setFragment(name : String){
        var tran = supportFragmentManager.beginTransaction()

        when(name){
            "my_home" -> {
                tran.replace(R.id.container,myHomeFragment)
            }

            "my_group" -> {
                tran.replace(R.id.container,myGroupFragment)
            }
        }

        tran.commit()
    }
}