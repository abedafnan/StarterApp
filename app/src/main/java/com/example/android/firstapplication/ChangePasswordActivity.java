package com.example.android.firstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_done) {
            if (checkPasswordMatch())
                finish();
            else Toast.makeText(ChangePasswordActivity.this,
                    "Passwords don't match!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean checkPasswordMatch() {
        EditText newPassField = findViewById(R.id.new_pass_field);
        String newPassword = newPassField.getText().toString();
        EditText confirmPassField = findViewById(R.id.confirm_pass_field);
        String confirmPassword = confirmPassField.getText().toString();

        return newPassword.equals(confirmPassword);

    }
}
