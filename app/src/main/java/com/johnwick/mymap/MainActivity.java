package com.johnwick.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = mDatabase.getReference();
    private Button btnSave;
    private Button btnProceed;
    private EditText editTextLocationName;
    private EditText editTextLocationLat;
    private EditText editTextLocationLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRef=FirebaseDatabase.getInstance().getReference().child("Users");
        editTextLocationName=(EditText)findViewById(R.id.editTextLocationName);
        editTextLocationLat=(EditText)findViewById(R.id.editTextLocationLat);
        editTextLocationLong=(EditText)findViewById(R.id.editTextLocationLong);
        btnSave=(Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnProceed=(Button)findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,MapsActivity.class); //Add it later
                startActivity(i);
            }
        });
    }

    private void saveUserInformation(){
        String name =editTextLocationName.getText().toString().trim();
        double latitude= Double.parseDouble(editTextLocationLat.getText().toString().trim());
        double longitude= Double.parseDouble(editTextLocationLong.getText().toString().trim());

        String key = mRef.push().getKey();


        UserInformation userInformation=new UserInformation(name,latitude,longitude);
        mRef.child(key).setValue(userInformation);
        Toast.makeText(this,"Successfully Saved",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view){
        if(view==btnProceed){
            finish();
        }
        if(view==btnSave){
            saveUserInformation();
            editTextLocationName.getText().clear();
            editTextLocationLat.getText().clear();
            editTextLocationLong.getText().clear();
        }
    }
}
