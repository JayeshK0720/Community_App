package com.jktech.community_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class customer_info extends AppCompatActivity {

    private EditText editTextFullName, editTextDOB, editTextMobile, editTextAddress;
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxTermsConditions;
    private Button buttonSave, buttonCancel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_info);

        // Initialize views
        editTextFullName = findViewById(R.id.editText_register_full_name);
        editTextDOB = findViewById(R.id.editText_register_dob);
        editTextMobile = findViewById(R.id.editText_register_mobile);
        editTextAddress = findViewById(R.id.editText_register_address);
        radioGroupGender = findViewById(R.id.radio_group_register_gender);
        checkBoxTermsConditions = findViewById(R.id.checkBox_terms_conditions);
        buttonSave = findViewById(R.id.button_save);
        buttonCancel = findViewById(R.id.button_cancel);
        progressBar = findViewById(R.id.progressBar);

        // Setup save button click listener
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomerInfo();
            }
        });

        // Setup cancel button click listener
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the design page
                Intent intent = new Intent(customer_info.this, design.class);
                startActivity(intent);
                finish(); // Optional: Call finish() if you want to close the RegisterActivity
            }
        });
    }

    private void saveCustomerInfo() {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Get input data
        String fullName = editTextFullName.getText().toString().trim();
        String dob = editTextDOB.getText().toString().trim();
        String mobile = editTextMobile.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = (selectedGenderButton != null) ? selectedGenderButton.getText().toString() : "";

        boolean termsAccepted = checkBoxTermsConditions.isChecked();

        // Validate input
        if (fullName.isEmpty() || dob.isEmpty() || mobile.isEmpty() || address.isEmpty() || gender.isEmpty() || !termsAccepted) {
            Toast.makeText(this, "Please fill all the required fields and accept the terms.", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        // Perform save operation (e.g., send data to server)
        // ...

        // Hide progress bar
        progressBar.setVisibility(View.GONE);

        // Show success message
        Toast.makeText(this, "Customer information saved successfully.", Toast.LENGTH_LONG).show();

        // Clear all fields
        clearFields();
    }

    private void clearFields() {
        editTextFullName.setText("");
        editTextDOB.setText("");
        editTextMobile.setText("");
        editTextAddress.setText("");
        radioGroupGender.clearCheck();
        checkBoxTermsConditions.setChecked(false);
    }
}
