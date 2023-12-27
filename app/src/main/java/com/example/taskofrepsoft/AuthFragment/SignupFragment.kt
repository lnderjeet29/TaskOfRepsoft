package com.example.taskofrepsoft.AuthFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taskofrepsoft.MainActivity2
import com.example.taskofrepsoft.R
import com.example.taskofrepsoft.databinding.FragmentSignupBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class signupFragment : Fragment() {

    private val binding:FragmentSignupBinding by lazy {
        FragmentSignupBinding.inflate(layoutInflater)
    }
    private lateinit var mAuth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mAuth=FirebaseAuth.getInstance()
        binding.txtAlreadySign.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
        binding.apply {
            btnSign.setOnClickListener {
                if(password.text.toString()==confirmPassword.text.toString()) {
                    if(password.text.toString().length<6){
                        Toast.makeText(requireContext(),"enter 6 digit password",Toast.LENGTH_LONG).show()
                    }else {
                        signUp(Email.text.toString(), password.text.toString())
                    }
                }else{
                    confirmPassword.error="enter same password"
                }
            }
        }
        return binding.root
    }

    private fun signUp(email: String, password: String) {
        try {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        val intent= Intent(requireActivity(), MainActivity2::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(requireContext(),"wrong credentials...", Toast.LENGTH_LONG).show()
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}