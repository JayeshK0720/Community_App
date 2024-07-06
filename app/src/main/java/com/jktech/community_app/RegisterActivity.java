package com.jktech.community_app;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    private ScrollView scrollView;
    private RelativeLayout RLName, RLEmail, RLGender, RLMobile, RLPwd, RLCpwd;
    private EditText editTextFullName, editTextEmail, editTextMobile, editTextPassword, editTextCPassword;
    private RadioGroup radioGroupGender;
    private RadioButton radioFemale, radioMale;
    private CheckBox checkBoxTermsConditions;
    private Button buttonRegister;
    private ProgressBar progressBar;
    private ImageView imageViewShowHidePwd, imageViewShowHideCpwd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        swipeContainer = findViewById(R.id.swipeContainer);
//        scrollView = findViewById(R.id.scrollView);
        RLName = findViewById(R.id.RL_name);
        RLEmail = findViewById(R.id.RL_email);
        RLGender = findViewById(R.id.RL_gender);
        RLMobile = findViewById(R.id.RL_mobile);
        RLPwd = findViewById(R.id.RL_pwd);
        RLCpwd = findViewById(R.id.RL_Cpwd);

        editTextFullName = findViewById(R.id.editText_register_full_name);
        editTextEmail = findViewById(R.id.editText_register_email);
        editTextMobile = findViewById(R.id.editText_register_mobile);
        editTextPassword = findViewById(R.id.editText_register_password);
        editTextCPassword = findViewById(R.id.editText_register_Cpassword);

        radioGroupGender = findViewById(R.id.radio_group_register_gender);
        radioFemale = findViewById(R.id.radio_female);
        radioMale = findViewById(R.id.radio_male);

        checkBoxTermsConditions = findViewById(R.id.checkBox_terms_conditions);
        buttonRegister = findViewById(R.id.button_register);
        progressBar = findViewById(R.id.progressBar);

        imageViewShowHidePwd = findViewById(R.id.imageView_show_hide_pwd);
        imageViewShowHideCpwd = findViewById(R.id.imageView_show_hide_Cpwd);

        // Setup swipe to refresh
        swipeContainer.setOnRefreshListener(() -> {
            // Perform action on swipe refresh
            swipeContainer.setRefreshing(false);
        });

        // Show/Hide Password functionality
        imageViewShowHidePwd.setOnClickListener(v -> {
            if (editTextPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                imageViewShowHidePwd.setImageResource(R.drawable.ic_show_pwd); // Change icon
                editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_pwd); // Change icon
                editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        imageViewShowHideCpwd.setOnClickListener(v -> {
            if (editTextCPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                imageViewShowHideCpwd.setImageResource(R.drawable.ic_show_pwd); // Change icon
                editTextCPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                imageViewShowHideCpwd.setImageResource(R.drawable.ic_hide_pwd); // Change icon
                editTextCPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        // Register button click listener
        buttonRegister.setOnClickListener(v -> {
            String fullName = editTextFullName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String mobile = editTextMobile.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextCPassword.getText().toString().trim();

            if (validateInputs(fullName, email, mobile, password, confirmPassword)) {
                progressBar.setVisibility(View.VISIBLE);
                registerUser(email, password);
            }
        });
    }

    private boolean validateInputs(String fullName, String email, String mobile, String password, String confirmPassword) {
        if (fullName.isEmpty()) {
            editTextFullName.setError("Full Name is required");
            editTextFullName.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return false;
        }

        if (mobile.isEmpty()) {
            editTextMobile.setError("Mobile number is required");
            editTextMobile.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return false;
        }

        if (confirmPassword.isEmpty()) {
            editTextCPassword.setError("Confirm Password is required");
            editTextCPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            editTextCPassword.setError("Passwords do not match");
            editTextCPassword.requestFocus();
            return false;
        }

        if (!checkBoxTermsConditions.isChecked()) {
            Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            checkBoxTermsConditions.requestFocus();
            return false;
        }

        return true;
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Registration success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                            // You can also start a new activity here if needed
                        } else {
                            // If registration fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
