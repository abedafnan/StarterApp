package com.example.android.firstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change Password");

        EditText currentPassField = findViewById(R.id.current_pass_field);

    }

    public boolean chechPasswordMatch() {
        EditText newPassField = findViewById(R.id.new_pass_field);
        String newPassword = newPassField.getText().toString();
        EditText confirmPassField = findViewById(R.id.confirm_pass_field);
        String confirmPassword = confirmPassField.getText().toString();

        return newPassword.equals(confirmPassword);

    }
}
