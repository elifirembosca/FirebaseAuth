package com.example.firebaseauth.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.firebaseauth.R
import com.example.firebaseauth.databinding.FragmentLoginBinding
import com.example.firebaseauth.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setUi(){
        binding.run {
            tvLogin.setOnClickListener{
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
            registerButton.setOnClickListener {
                signUpUser()
            }
        }
    }

    private fun signUpUser() {
        val email = binding.edEmail.text.toString()
        val pass = binding.edPassword.text.toString()
        if(email.isBlank() || pass.isBlank()){
            Toast.makeText(context, "Email or Password can't be blank!", Toast.LENGTH_SHORT).show()
        }else{
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(requireActivity()){
                if (it.isSuccessful) {
                    Toast.makeText(context, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(requireView()).navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
                } else {
                    Toast.makeText(context, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}