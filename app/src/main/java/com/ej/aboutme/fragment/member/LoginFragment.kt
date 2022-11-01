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
        act.binding.bottomAppBar.visibility = View.GONE
        act.binding.floatingActionButton.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.email.editText!!.setText("test@test.com")
        binding.password.editText!!.setText("test")
        binding.loginBtn.setOnClickListener {
            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            val loginDto = LoginDto(email,password)
            memberViewModel.loginMember(loginDto)
        }
        binding.signupBtn.setOnClickListener{
            act.setFragment("signup")
        }
        memberViewModel.loginResult.observe(viewLifecycleOwner){
            Toast.makeText(act,"로그인 성공", Toast.LENGTH_LONG).show()
            queryPreferences.setAutoLogin(requireContext(),it.memberId,it.email)
            act.setFragment("my_home")
        }

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}