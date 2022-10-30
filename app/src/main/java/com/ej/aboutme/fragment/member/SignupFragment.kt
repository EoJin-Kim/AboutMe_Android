package com.ej.aboutme.fragment.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.databinding.FragmentLoginBinding
import com.ej.aboutme.databinding.FragmentSignupBinding
import com.ej.aboutme.dto.request.SignupDto
import com.ej.aboutme.dto.response.ResponseStatus
import kotlin.math.log


class SignupFragment : Fragment() {
    val aboutMeFetchr = AboutMeFetchr()

    val act : MainActivity by lazy { activity as MainActivity }
    lateinit var signupFragmentBinding : FragmentSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        signupFragmentBinding = FragmentSignupBinding.inflate(LayoutInflater.from(container!!.context),container,false)

        val nameView  = signupFragmentBinding.name
        val emailView = signupFragmentBinding.email
        val passwordView = signupFragmentBinding.password
        signupFragmentBinding.joinBtn.setOnClickListener {
            val name = nameView.editText?.text.toString()
            val email = emailView.editText?.text.toString()
            val password = passwordView.editText?.text.toString()
            val signupDto = SignupDto(name,email,password)
//            aboutMeFetchr.test()
            val signupData : LiveData<ResponseStatus> = aboutMeFetchr.signup(signupDto)
            signupData.observe(viewLifecycleOwner){
                if(it == ResponseStatus.SUCCESS){
                    Toast.makeText(act,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    act.setFragment("login")
                }
            }
        }

        return signupFragmentBinding.root
    }

    companion object {
        fun newInstance(): SignupFragment {
            return SignupFragment()
        }
    }
}