package com.example.android.firstapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView profileImage;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "main activity created");

        // When profile image is pressed
        // Goes to the gallery for an image pick
        profileImage = findViewById(R.id.profile_image_view);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeProfileImage();
            }
        });

        // When logout button is pressed
        // Goes back to login screen
        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(); finish();
            }
        });

        // When change password button is pressed
        // Goes to change password screen
        Button changePassButton = findViewById(R.id.change_password_button);
        changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePass();
            }
        });

        // Gets the username and password data from the login activity
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        // Displays the username on the screen
        TextView userTextView = findViewById(R.id.username_text_view);
        userTextView.setText(username);
    }

    // Handles transferring to the gallery
    public void changeProfileImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    // Gets back the data of the chosen image from the gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    profileImage.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,
                            "Something went wrong", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this,
                        "You haven't picked an Image",Toast.LENGTH_LONG).show();
            }
        }
    }

    // Handles transferring back to the login screen
    public void logout() {
        getSharedPreferences("login", MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).apply();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // Handles transferring to the change password screen
    // Sends password data to it
    public void goToChangePass() {
        Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}
