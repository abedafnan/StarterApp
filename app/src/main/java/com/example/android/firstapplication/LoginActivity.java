package com.example.android.firstapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    String  mUsername = "my username";
    String mPassword = "abc123";
    private EditText usernameField;
    private EditText passwordField;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        // keeps the user logged in
        if (preferences.getBoolean("isLoggedIn", false)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        usernameField = findViewById(R.id.username_field);
        passwordField = findViewById(R.id.password_field);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginCheck()) {
                    login(); finish();
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

        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", false);
    }

    public boolean loginCheck() {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        return mUsername.equalsIgnoreCase(username) & mPassword.equals(password);
    }

    public void login() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("username", mUsername);
        intent.putExtra("password", mPassword);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, 2);
    }

    public void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                mUsername = data.getStringExtra("username");
                usernameField.setText(mUsername);
                mPassword = data.getStringExtra("password");
                passwordField.setText(mPassword);
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                mPassword = data.getStringExtra("newPassword");
            }
        }
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
