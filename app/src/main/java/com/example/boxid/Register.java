package com.example.boxid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    EditText et_nama_lengkap, et_alamat, et_email, et_password;
    Button bt_register;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_nama_lengkap = (EditText) findViewById(R.id.et_nama);
        et_alamat = (EditText) findViewById(R.id.et_alamat);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = et_nama_lengkap.getText().toString();
                String alamat = et_alamat.getText().toString();
                String email = et_email.getText().toString();
                String passsword = et_password.getText().toString();
                Map<String, Object> user_account = new HashMap<>();
                user_account.put("nama", nama);
                user_account.put("alamat", alamat);
                user_account.put("email", email);
                user_account.put("password", passsword);

                db.collection("user_account")
                        .add(user_account)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {

                    Toast.makeText(Register.this,"Failed",Toast.LENGTH_SHORT).show();


                }
                        });

            }
        });

    }
}