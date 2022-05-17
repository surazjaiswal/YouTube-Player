package com.practice.ytplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Form extends AppCompatActivity {

    public static final int CAMERA_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;

    EditText txt_name,txt_email;
    ImageButton imageButton;

    Button btn_submit;
    String name,email;
    Bitmap bitmap;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        imageButton = findViewById(R.id.btn_camera);
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);
        btn_submit = findViewById(R.id.btn_submit);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraPermissions();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txt_name.getText().toString().trim();
                email = txt_email.getText().toString().trim();

                if(name.isEmpty() || email.isEmpty()){
                    Toast.makeText(Form.this, "Please Provide All the Details", Toast.LENGTH_SHORT).show();
                }
                else if(!email.matches(emailPattern)){
                    Toast.makeText(Form.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if(bitmap == null){
                    Toast.makeText(Form.this, "No Image Provided", Toast.LENGTH_SHORT).show();
                }
                else{
                    // forward the data to next page
//                    Toast.makeText(Form.this, "Valid Email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Form.this,Profile.class);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email);
                    intent.putExtra("img",bitmap);

                    startActivity(intent);
                }
            }
        });
    }
    void cameraPermissions(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_CODE);
        }else{
            openCamera();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }else{
            Toast.makeText(this, "Camera Permission Required", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE){
            bitmap = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(bitmap);
            Toast.makeText(this, "Image Received", Toast.LENGTH_SHORT).show();
        }
    }
}