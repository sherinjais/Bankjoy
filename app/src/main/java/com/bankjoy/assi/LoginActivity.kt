package com.bankjoy.assi

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bankjoy.assi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.usernameEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.passwordEditText.requestFocus()
                true
            } else {
                false
            }
        }

        binding.passwordEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                hideKeyboard(v)
                true
            } else {
                false
            }
        }
        binding.usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.usernameEditText.setTextColor(Color.BLACK)
                binding.loginButton.setBackgroundResource(R.drawable.d_rounded_corner)

                binding.errorTextView.isErrorEnabled = false
                binding.errorTextView.error = null
                binding.errorTextView.boxStrokeColor = ContextCompat.getColor(this@LoginActivity, R.color.black)

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.passwordEditText.setTextColor(Color.BLACK)
                binding.loginButton.setBackgroundResource(R.drawable.d_rounded_corner)

                binding.errorTextView.isErrorEnabled = false
                binding.errorTextView.error = null
                binding.errorTextView.boxStrokeColor = ContextCompat.getColor(this@LoginActivity, R.color.black)

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (binding.checkbox.isChecked) {
                binding.errorTextViews.visibility = View.GONE
                val isValid = validateInputs(username, password)
                updateUI(isValid)
            } else {
                binding.errorTextViews.text = getString(R.string.hint_error_chk)
                binding.errorTextViews.visibility = View.VISIBLE
            }
        }

    }

    private fun validateInputs(username: String, password: String): Boolean {
        return username == "admin" && password == "admin123"
    }

    private fun updateUI(isValid: Boolean) {
        if (isValid) {
            binding.usernameEditText.setTextColor(getColor(R.color.green))
            binding.passwordEditText.setTextColor(getColor(R.color.green))
            binding.loginButton.setBackgroundResource(R.drawable.v_rounded_corner)

        } else {
            binding.errorTextView.error = getString(R.string.hint_error)
            binding.usernameEditText.setTextColor(Color.RED)
            binding.passwordEditText.setTextColor(Color.RED)
        }
    }
    private fun hideKeyboard(view: TextView) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
