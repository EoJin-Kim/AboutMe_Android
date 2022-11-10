package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentLoginBinding
import com.ej.aboutme.dto.request.LoginDto
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.MemberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    val act : MainActivity by lazy { activity as MainActivity }
    val queryPreferences = QueryPreferences()
    private val memberViewModel: MemberViewModel by activityViewModels()
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
        super.onViewCreated(view, savedInstanceState)

        drawUi()

        binding.loginBtn.setOnClickListener {
            login()
        }

        binding.signupBtn.setOnClickListener{
            moveSignUp()
        }

        memberViewModel.loginResult.observe(viewLifecycleOwner){
            Toast.makeText(act,"로그인 성공", Toast.LENGTH_LONG).show()
            queryPreferences.setAutoLogin(requireContext(),it.memberId,it.email)
            act.setFragment(MemberHomeFragment.TAG)
        }
    }

    private fun drawUi() {
        binding.email.editText!!.setText("test@test.com")
        binding.password.editText!!.setText("test")
    }

    private fun moveSignUp() {
        act.setFragment(SignupFragment.TAG)
    }

    private fun login() {
        val email = binding.email.editText?.text.toString()
        val password = binding.password.editText?.text.toString()
        val loginDto = LoginDto(email, password)
        memberViewModel.loginMember(loginDto)
    }

    companion object {
        val TAG = "LoginFragment"
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}