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
import com.ej.aboutme.dto.response.ResponseStatus
import com.ej.aboutme.preferences.QueryPreferences


class LoginFragment : Fragment() {
    val act : MainActivity by lazy { activity as MainActivity }
    val aboutMeFetchr = AboutMeFetchr()
    val queryPreferences = QueryPreferences()

    lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        act.binding.bottomAppBar.visibility = View.GONE
        act.binding.floatingActionButton.visibility = View.GONE

        binding.email.editText!!.setText("test@test.com")
        binding.password.editText!!.setText("test")

        binding.loginBtn.setOnClickListener {
            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            val loginDto = LoginDto(email,password)
            val loginResult : LiveData<ResponseDto<LoginResultDto>>  = aboutMeFetchr.login(loginDto)
            loginResult.observe(viewLifecycleOwner){
                if(it.status == ResponseStatus.SUCCESS){
                    Toast.makeText(act,"로그인 성공", Toast.LENGTH_LONG).show()
                    queryPreferences.setAutoLogin(requireContext(),it.response.memberId,it.response.email)
                    act.setFragment("my_home")
                }
            }
        }
        binding.signupBtn.setOnClickListener{
            act.setFragment("signup")
        }

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}