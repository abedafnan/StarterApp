package com.example.android.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPasswordMatch())
                    registerCompletion();
                else
                    Toast.makeText(RegisterActivity.this,
                            "Passwords don't match!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean checkPasswordMatch() {
        EditText passwordField = findViewById(R.id.password_field);
        String password = passwordField.getText().toString();
        EditText passConfirmField = findViewById(R.id.password_confirmation_field);
        String passConfirmation = passConfirmField.getText().toString();

        return password.equals(passConfirmation);
    }

    public void registerCompletion() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
