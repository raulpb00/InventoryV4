package es.raulprieto.inventory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.textfield.TextInputEditText;

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
        binding.edPassword2.addTextChangedListener(new SignUpWatcher(binding.edPassword1,binding.edPassword2));
        binding.edEmail.addTextChangedListener(new SignUpWatcher(binding.edUser));


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
        if (validateUser(binding.edUser.getText().toString()) && validatePassword(binding.edPassword1.getText().toString(), binding.edPassword2.getText().toString()) && validateEmail(binding.edEmail.getText().toString())) {

            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Has cometido un error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method checks if the username introduced meets requirements:
     * 1. Can't be empty (no null)
     *
     * @param user Username introduced by the user
     * @return boolean
     */
    private boolean validateUser(String user) {
        if (TextUtils.isEmpty(user))
            binding.tilUser.setError(getString(R.string.errUserEmpty));

        return true;
    }

    /**
     * This method checks if the password introduced (at both fields) meets requirements:
     * 1. Same passwords (Password1 == Password2)
     * 2. Min size is 8, max is 12 and it must have one Capital and one number
     * 3. Can't be empty (no null)
     *
     * @param pass1 Password 1 introduced by the user
     * @param pass2 Password 2 introduced by the user
     * @return boolean
     */
    private boolean validatePassword(String pass1, String pass2) {

        return pass1.equals(pass2) && CommonUtils.isPasswordValid(pass1);
    }

    /**
     * This method checks if the email introduced meets requirements:
     *
     * @param email Email introduced by the user
     * @return boolean
     */
    private boolean validateEmail(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    class SignUpWatcher implements TextWatcher {
        private View view;
        private View view2;

        SignUpWatcher(View view) {
            this.view = view;
        }

        SignUpWatcher(View view, View view2) {
            this.view = view;
            this.view2 = view;
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
                case R.id.edPassword1:
                    validatePassword(((EditText) view).getText().toString(),((EditText) view2).getText().toString());
                    break;
                case R.id.edEmail:
                    validateEmail(((EditText) view).getText().toString());
                    break;
                default:
                    break;
            }
        }
    }
}
