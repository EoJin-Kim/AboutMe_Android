package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.ej.aboutme.MainActivity
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.databinding.FragmentSignupBinding
import com.ej.aboutme.dto.request.SignupDto
import com.ej.aboutme.dto.response.ResponseStatus
import com.ej.aboutme.viewmodel.MemberViewModel


class SignupFragment : Fragment() {
    val aboutMeFetchr = AboutMeFetchr()
    private val memberViewModel: MemberViewModel by activityViewModels()
    val act : MainActivity by lazy { activity as MainActivity }
    lateinit var binding : FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(LayoutInflater.from(container!!.context),container,false)

        val nameView  = binding.name
        val emailView = binding.email
        val passwordView = binding.password
        binding.joinBtn.setOnClickListener {
            val name = nameView.editText?.text.toString()
            val email = emailView.editText?.text.toString()
            val password = passwordView.editText?.text.toString()
            val signupDto = SignupDto(name,email,password)

            memberViewModel.signUp(signupDto)

        }

        memberViewModel.resonseStatus.observe(viewLifecycleOwner){
            if(it == ResponseStatus.SUCCESS){
                Toast.makeText(act,"회원가입 성공",Toast.LENGTH_SHORT).show()
                act.setFragment("login")
            }
        }
        return binding.root
    }

    companion object {
        fun newInstance(): SignupFragment {
            return SignupFragment()
        }
    }
}