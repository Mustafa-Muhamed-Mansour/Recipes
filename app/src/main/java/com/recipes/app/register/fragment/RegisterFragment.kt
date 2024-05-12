package com.recipes.app.register.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.recipes.app.register.viewmodel.RegisterViewModel
import com.recipes.app.utils.CryptoData
import com.recipes.data.datasource.local.datastore.PasswordDataStore
import com.recipes.data.datasource.local.datastore.PrefDataStore
import com.recipes.data.datasource.local.datastore.ProtoDataStore
import com.recipes.databinding.FragmentRegisterBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import java.util.Base64


class RegisterFragment : Fragment() {

    private lateinit var _binding: FragmentRegisterBinding
    private val binding get() = _binding

    val viewModel = RegisterViewModel(getKoin().get())

    private val cryptoData by lazy { CryptoData() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickedButtonLogin()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun clickedButtonLogin() {
        binding.btnRegister.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_SHORT)
                    .show()
                binding.editEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter your password", Toast.LENGTH_SHORT)
                    .show()
                binding.editPassword.requestFocus()
                return@setOnClickListener
            } else {
                createAccount(email, password)
            }
        }

        binding.txtPassword.setOnClickListener {
            convertPasswordFromEncryptToDecrypt()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createAccount(email: String, password: String) {
        viewModel.createAccount(email, password)
        collectFromViewModel(email, password)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun collectFromViewModel(email: String, password: String) {
        saveUserPassword(password)
        saveUserObject(FirebaseAuth.getInstance().uid.toString(), email)
        lifecycleScope.launch {
            viewModel.register.collect {
                if (it == true) {
                    Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Please try again later", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    // Save User Password by 1 way ---> Preference DataStore with using encrypt
    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveUserPassword(password: String) {
        val byte = password.toByteArray()
        val encryptedText = Base64.getEncoder().encodeToString(cryptoData.encrypt(byte = byte))
        viewModel.saveAnPassword(password = encryptedText)
    }

    // Save User Object by 2 way ---> ProtoType DataStore
    private fun saveUserObject(id: String, email: String) {
        viewModel.saveUserObject(id = id, email = email)
    }

    // Read DataStore
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun readDataStore() {
        // Read DataStore by way Preference
        lifecycleScope.launch {
            requireContext().PrefDataStore.data.collect {
                /* Here User_Password alone if not working */
                val password = it[PasswordDataStore.USER_PASSWORD] ?: ""
                binding.txtPassword.text = "Password:- $password"
            }
        }

        // Read DataStore by way ProtoType
        requireContext().ProtoDataStore.data.onEach {
            binding.txtEmail.text = "E-mail:- ${it.email}"
        }.launchIn(lifecycleScope)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        readDataStore()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun convertPasswordFromEncryptToDecrypt() {
        val password = requireContext().PrefDataStore.data.map {
            it[PasswordDataStore.USER_PASSWORD] ?: ""
        }
        lifecycleScope.launch {
            val originalText = cryptoData.decrypt(Base64.getDecoder().decode(password.first()))?.decodeToString()
            binding.txtPassword.text = "Password:- $originalText"
        }
    }
}