package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.ej.aboutme.MainActivity
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.databinding.FragmentLoginBinding
import com.ej.aboutme.dto.response.ResponseDto
import com.ej.aboutme.dto.request.LoginDto
import com.ej.aboutme.dto.response.LoginResultDto


class LoginFragment : Fragment() {
    val act : MainActivity by lazy { activity as MainActivity }
    val aboutMeFetchr = AboutMeFetchr()


    lateinit var loginFragmentBinding : FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        act.binding.bottomAppBar.visibility = View.GONE
        act.binding.floatingActionButton.visibility = View.GONE
        // Inflate the layout for this fragment
        loginFragmentBinding = FragmentLoginBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        val emailView = loginFragmentBinding.email
        val passwordView = loginFragmentBinding.password
        loginFragmentBinding.loginBtn.setOnClickListener {
            val email = emailView.editText?.text.toString()
            val password = passwordView.editText?.text.toString()
            val loginDto = LoginDto(email,password)
            val loginResult : LiveData<ResponseDto<LoginResultDto>>  = aboutMeFetchr.login(loginDto)
            loginResult.observe(viewLifecycleOwner){
                if(it.status == "success"){
                    Toast.makeText(act,"로그인 성공", Toast.LENGTH_SHORT).show()
                    act.setFragment("my_home","test@test.com")
                }
            }
        }
        loginFragmentBinding.signupBtn.setOnClickListener{
            act.setFragment("signup")
        }

        return loginFragmentBinding.root
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}