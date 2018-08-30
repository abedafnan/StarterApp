package com.example.android.firstapplication;

import android.content.Intent;
import android.content.SharedPreferences;
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

        preferences = getSharedPreferences("login", MODE_PRIVATE);
        // Keeps the user logged in if they already are
        if (preferences.getBoolean("isLoggedIn", false)) {
            login();
            finish();
        }

        usernameField = findViewById(R.id.username_field);
        passwordField = findViewById(R.id.password_field);

        // When the login button is pressed
        // Checks the user inputs and goes to profile if they are valid
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginCheck()) {
                    login();
                    preferences.edit().putBoolean("isLoggedIn", true).apply();
                    finish();
                }
            }
        });

        // When the register button is pressed
        // Goes to register screen
        TextView registerTextView = findViewById(R.id.register_text_view);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    // Checks if the username and password are valid
    // Displays an error message if they aren't
    public boolean loginCheck() {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (mUsername.equalsIgnoreCase(username)) {
            if (mPassword.equals(password))
                return true;
            else
                passwordField.setError("Wrong password");
        } else
            usernameField.setError("Invalid username");

        return false;
    }

    // Handles transferring to the profile screen after the login procedure is successful
    public void login() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("username", mUsername);
        intent.putExtra("password", mPassword);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // Handles transferring to the register screen
    public void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(intent, 1);
    }

    // Gets data back from the register activity for login
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
        }
    }
}
