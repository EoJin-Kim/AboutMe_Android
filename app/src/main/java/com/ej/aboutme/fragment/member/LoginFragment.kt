package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    val act : MainActivity by lazy { activity as MainActivity }


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