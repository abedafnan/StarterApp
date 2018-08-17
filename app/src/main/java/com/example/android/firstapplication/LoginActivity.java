package com.example.android.firstapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    String mUsername = "my username";
    String mPassword = "abc123";
    EditText usernameField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.username_field);
        passwordField = findViewById(R.id.password_field);

        Intent intent = getIntent();
        if (intent != null) {
            mUsername = intent.getStringExtra("username");
            mPassword = intent.getStringExtra("password");
        }

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginCheck()) {
                    login();
                    finish();
                } else {
                    showAlert();
                }
            }
        });

        TextView registerTextView = findViewById(R.id.register_text_view);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public boolean loginCheck() {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        return username.equalsIgnoreCase(mUsername) & password.equals(mPassword);
    }

    public void login() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("username", mUsername);
        intent.putExtra("password", mPassword);
        startActivity(intent);
    }

    public void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
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
