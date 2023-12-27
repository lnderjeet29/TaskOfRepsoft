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
import com.example.taskofrepsoft.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class loginFragment : Fragment() {

 private val binding:FragmentLoginBinding by lazy {
     FragmentLoginBinding.inflate(layoutInflater)
 }
    private lateinit var mAuth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAuth=FirebaseAuth.getInstance()
        // Inflate the layout for this fragment
        binding.txtAlreadySign.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        binding.btnSign.setOnClickListener {
            signInUser(binding.Email.text.toString(),binding.password.text.toString())
        }
        return binding.root
    }

    private fun signInUser(email: String, password: String) {
        try {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        val intent=Intent(requireActivity(),MainActivity2::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(requireContext(),"wrong credentials...",Toast.LENGTH_LONG).show()
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}