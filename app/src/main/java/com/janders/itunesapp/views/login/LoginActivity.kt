package com.janders.itunesapp.views.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout
import com.janders.itunesapp.R
import com.janders.itunesapp.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        initializeTextChangedListeners()
        initializeClickListeners()
    }

    private fun initializeTextChangedListeners(){
        userEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                updateLoginButtonState()
            }
        })

        userEt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateUser()
            } else {
                showInputLayoutError(inputLayoutUser, null, false)
            }
        }

        passwordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) = Unit

            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().isEmpty()) {
                    showInputLayoutError(inputLayoutPassword, getString(R.string.error_empty_field), true)
                } else {
                    showInputLayoutError(inputLayoutPassword, null, false)
                }
                updateLoginButtonState()
            }
        })
    }

    private fun initializeClickListeners(){
        login_but.setOnClickListener {
            hideKeyboard(this)
            if (hasNetworkConnection()) {
                showProgressDialog(getString(R.string.message_title_login),getString(R.string.message_please_wait), indeterminate = false, cancelable = false)
                val mHandler1 = Handler()
                mHandler1.postDelayed({
                    hideProgressDialog()
                    /*goToActivity()
                    finish()*/
                }, 1750)
            } else {
                showSimpleToast(resources.getString(R.string.message_not_connected))
            }
        }
    }

    private fun <T: Activity>goToActivity(classType: Class<T>, flags: Array<Int>) {
        if (flags.isEmpty())
            startActivity(Intent(this, classType))
        else {
            val intent = Intent(this, classType)
            flags.forEachIndexed { index, _ ->
                intent.addFlags(flags[index])
            }
            startActivity(intent)
        }
    }

    //region Form validations

    private fun validateUser(): Boolean {
        val email = userEt.text.toString().trim()

        if(email.isEmpty()) {
            showInputLayoutError(inputLayoutUser, getString(R.string.error_empty_field), true)
            return false
        }

        inputLayoutUser.isErrorEnabled = false
        return true
    }

    private fun updateLoginButtonState(){
        login_but.isEnabled = userEt.text?.length != 0
                && passwordEt.text?.length != 0
    }

    private fun showInputLayoutError(inputLayout: TextInputLayout, error: String?, isEnabled: Boolean){
        inputLayout.error = error
        inputLayout.isErrorEnabled = isEnabled
    }
    
    //endregion
}
