package com.practice.ytplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Form extends AppCompatActivity {

    EditText txt_name,txt_email;
    ImageButton imageButton;

    Button btn_submit;

    String name,email;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        imageButton = findViewById(R.id.btn_camera);
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);
        btn_submit = findViewById(R.id.btn_submit);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txt_name.getText().toString().trim();
                email = txt_email.getText().toString().trim();

                if(!email.matches(emailPattern)){
                    Toast.makeText(Form.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    // forward the data to next page
                    Toast.makeText(Form.this, "Valid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}