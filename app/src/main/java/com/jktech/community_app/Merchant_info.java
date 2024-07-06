package com.jktech.community_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Merchant_info extends AppCompatActivity {

    private EditText fullNameEditText, mobileEditText, addressEditText, aadharCardEditText, panCardEditText, gstinEditText, shopActEditText;
    private RadioGroup genderRadioGroup;
    private CheckBox termsCheckBox;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_merchant_info);

        // Initialize UI elements
        fullNameEditText = findViewById(R.id.editText_register_full_name);
        mobileEditText = findViewById(R.id.editText_register_mobile);
        addressEditText = findViewById(R.id.editText_register_email);
        aadharCardEditText = findViewById(R.id.editText_register_aadharcard);
        panCardEditText = findViewById(R.id.editText_register_pancard);
        gstinEditText = findViewById(R.id.editText_register_gstin);
        shopActEditText = findViewById(R.id.editText_register_shopact);
        genderRadioGroup = findViewById(R.id.radio_group_register_gender);
        termsCheckBox = findViewById(R.id.checkBox_terms_conditions);
        saveButton = findViewById(R.id.button_save);
        cancelButton = findViewById(R.id.button_cancel);

        // Set up cancel button to navigate back to the design activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Merchant_info.this, design.class);
                startActivity(intent);
                finish(); // Optional: Call finish() if you want to close the Merchant_info activity
            }
        });

        // Set up save button to handle form submission
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });
    }

    private void handleSubmit() {
        String fullName = fullNameEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String aadharCard = aadharCardEditText.getText().toString().trim();
        String panCard = panCardEditText.getText().toString().trim();
        String gstin = gstinEditText.getText().toString().trim();
        String shopAct = shopActEditText.getText().toString().trim();

        // Validate inputs
        if (fullName.isEmpty() || mobile.isEmpty() || address.isEmpty() || aadharCard.isEmpty() ||
                panCard.isEmpty() || gstin.isEmpty() || shopAct.isEmpty() || !termsCheckBox.isChecked()) {
            Toast.makeText(this, "Please fill all fields and accept terms and conditions", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
        String gender = selectedGenderRadioButton.getText().toString();

        // TODO: Perform actual data submission to the server or database here

        // Show a success message
        Toast.makeText(this, "Information saved successfully!", Toast.LENGTH_SHORT).show();

        // Optional: Navigate back to the design activity or another activity
        Intent intent = new Intent(Merchant_info.this, design.class);
        startActivity(intent);
        finish();
    }
}
