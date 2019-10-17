package es.raulprieto.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.raulprieto.inventory.databinding.ActivitySignupBinding;

/**
 * Class used to Sign Up a new user in our app
 * @author Raúl Prieto Bailón
 * @version 1.0
 */
public class SignUpActivity extends AppCompatActivity {
    ActivitySignupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);

        // Recolect

        //TODO completar datos faltantes

        binding.btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    /**
     * Method which checks if all of the TextInputLayout fields contents are valid
     */
    private void validate() {
        if (validateUser() && validatePassword() && validateEmail()){
            //1. Save the user in the DB
            
            //2. Send email to User
            
            //3. Change to Login window
            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * This method checks if the username introduced meets requirements
     * 1. Can't be empty (no null)
     * @return boolean
     */
    private boolean validateUser() {
        return true;
    }

    /**
     * This method checks if the password introduced (at both fields) meets requirements:
     * 1. Same passwords (Password1 == Password2)
     * 2. Min size is 8, one Capital and one number
     * 3. Can't be empty (no null)
     * @return boolean
     */
    private boolean validatePassword() {
        return true;
    }

    /**
     * This method checks if the email introduced meets requirements
     * @return boolean
     */
    private boolean validateEmail() {
        return true;
    }




}
