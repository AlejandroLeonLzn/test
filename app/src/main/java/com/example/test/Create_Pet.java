package com.example.test;

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

import java.util.HashMap;
import java.util.Map;


public class Create_Pet extends AppCompatActivity {

    Button add_btn;
    EditText a, b, c;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);

        this.setTitle("Crear Mascota");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mfirestore = FirebaseFirestore.getInstance();
        add_btn = findViewById(R.id.add_btn);
        a = findViewById(R.id.nombre);
        b = findViewById(R.id.edad);
        c = findViewById(R.id.color);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namepet = a.getText().toString().trim();
                String agepet = b.getText().toString().trim();
                String colorpet = c.getText().toString().trim();

                if (namepet.isEmpty() && agepet.isEmpty() && colorpet.isEmpty()){
                    Toast.makeText(Create_Pet.this, "ingresar los datos", Toast.LENGTH_SHORT).show();
                }else {
                    postPet(namepet, agepet, colorpet);
                }
            }

            private void postPet(String namepet, String agepet, String colorpet) {
                Map<String, Object> map = new HashMap<>();
                map.put("name",namepet);
                map.put("age",agepet);
                map.put("color",colorpet);
                mfirestore.collection("pet").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Creado Exitosamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error al ingresar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}