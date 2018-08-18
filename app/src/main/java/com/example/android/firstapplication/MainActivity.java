package com.example.android.firstapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImage = findViewById(R.id.profile_image_view);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeProfileImage();
            }
        });

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(); finish();
            }
        });

        Button changePassButton = findViewById(R.id.change_password_button);
        changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePass();
            }
        });

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        TextView userTextView = findViewById(R.id.username_text_view);
        userTextView.setText(username);
    }

    public void changeProfileImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

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
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                newPassword = data.getStringExtra("newPassword");
            }
        }
    }

    public void logout() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra("newPassword", newPassword);
        setResult(RESULT_OK);
        startActivity(intent);
    }

    public void goToChangePass() {
        Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}
