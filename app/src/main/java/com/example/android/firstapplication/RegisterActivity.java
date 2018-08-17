package com.example.android.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private EditText passConfirmField;
    private String username;
    private String password;
    private String passConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameField = findViewById(R.id.username_field);
        passwordField = findViewById(R.id.password_field);
        passConfirmField = findViewById(R.id.password_confirmation_field);

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPasswordMatch()) {
                    registerCompletion();
                    finish();
                } else
                    Toast.makeText(RegisterActivity.this,
                            "Passwords don't match!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean checkPasswordMatch() {
        password = passwordField.getText().toString();
        passConfirmation = passConfirmField.getText().toString();

        return password.equals(passConfirmation);
    }

    public void registerCompletion() {
        username = usernameField.getText().toString();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}
