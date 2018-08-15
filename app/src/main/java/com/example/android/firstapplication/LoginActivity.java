package com.example.android.firstapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usernameField;
    EditText passwordField;

    final String mUsername = "my username";
    final String mPassword = "abc123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginCheck()) {
                    login();
                } else {
                    showAlert();
                }
            }
        });
    }

    public boolean loginCheck() {
        usernameField = findViewById(R.id.username_field);
        String username = usernameField.getText().toString();
        passwordField = findViewById(R.id.password_field);
        String password = passwordField.getText().toString();

        if (username.equalsIgnoreCase(mUsername) & password.equals(mPassword)) {
            return true;
        } else
            return false;
    }

    public void login() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void showAlert() {
        AlertDialog.Builder alert  = new AlertDialog.Builder(LoginActivity.this);

        alert.setMessage("wrong username or password");
        alert.setTitle("Error Message");
        alert.setPositiveButton("Try Again", null);
        alert.setCancelable(true);
        alert.create().show();
    }
}
