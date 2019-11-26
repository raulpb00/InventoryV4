package es.raulprieto.inventory.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import es.raulprieto.inventory.R;
import es.raulprieto.inventory.databinding.ActivitySignupBinding;
import es.raulprieto.inventory.util.CommonUtils;

/**
 * Class used to Sign Up a new user in our app
 *
 * @author Raúl Prieto Bailón
 * @version 1.0
 */
public class SignUpActivity extends AppCompatActivity {
    ActivitySignupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        // Create a SignUpWatcher object and assign it to each View object
        binding.edUser.addTextChangedListener(new SignUpWatcher(binding.edUser));
        binding.edPassword.addTextChangedListener(new SignUpWatcher(binding.edPassword));
        binding.edEmail.addTextChangedListener(new SignUpWatcher(binding.edEmail));


        // Link Click listener to the button
        binding.btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    /* SIGNING UP A NEW USER IN AN APPLICATION
     * 1. Save the user in the DB
     * 2. Send email to User
     * 3. Change to Login window
     */

    /**
     * Method which checks if all of the TextInputLayout fields contents are valid
     */
    private void validate() {
        if (validateEmail(binding.edEmail.getText().toString())
                & validatePassword(binding.edPassword.getText().toString())
                & validateUser(binding.edUser.getText().toString())) {
            finish();
        } /*else {
            Toast.makeText(this, "Has cometido un error", Toast.LENGTH_SHORT).show();
        }*/
    }

    /**
     * This method checks if the username introduced meets requirements:
     * 1. Can't be empty (no null)
     *
     * @param user Username introduced by the user
     * @return boolean
     */
    private boolean validateUser(String user) {
        if (TextUtils.isEmpty(user)) {
            binding.tilUser.setError(getString(R.string.errUserEmpty));
            requestFocus(binding.edUser);
            return false;
        } else {
            binding.tilUser.setError(null);
            return true;
        }
    }

    /**
     * This method checks if the password introduced (at both fields) meets requirements:
     * 1. Min size is 8, max is 12 and it must have one Capital and one number
     * 2. Can't be empty (no null)
     *
     * @param pass1 Password 1 introduced by the user
     * @return boolean
     */
    private boolean validatePassword(String pass1) {
        if (CommonUtils.isPasswordValid(pass1)) {
            binding.tilPassword.setError(null);
            return true;
        } else {
            binding.tilPassword.setError(getString(R.string.errPassNotValid));
            requestFocus(binding.edPassword);
            return false;
        }
    }

    /**
     * This method checks if the email introduced meets requirements:
     * 1. It pass our regexp matcher
     *
     * @param email Email introduced by the user
     * @return boolean
     */
    private boolean validateEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError(null);
            return true;
        } else {
            binding.tilEmail.setError(getString(R.string.errEmailNotValid));
            requestFocus(binding.edEmail);
            return false;
        }
    }


    class SignUpWatcher implements TextWatcher {
        private View view;

        SignUpWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.edUser:
                    validateUser(((EditText) view).getText().toString());
                    break;
                case R.id.edPassword:
                    validatePassword(((EditText) view).getText().toString());
                    break;
                case R.id.edEmail:
                    validateEmail(((EditText) view).getText().toString());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This method opens the keyboard when a View (TextInputEditText) has got the focus
     */
    private void requestFocus(View view) {
        if (view.requestFocus())
            showSoftInput(view);
    }
    /**
     * Method that shows the keyboard (softInput)
     * @param view Element focused
     */
    public void showSoftInput(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    /**
     * Method used to hide the keyboard
     */
    public void hideSoftInput() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


}
