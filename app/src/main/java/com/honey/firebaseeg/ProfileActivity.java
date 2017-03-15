package com.honey.firebaseeg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private Firebase mRootRef;
    private EditText name,age;
    Button buttonlogout,Done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name =(EditText)findViewById(R.id.Name);
        age=(EditText)findViewById(R.id.Age);
        Done=(Button)findViewById(R.id.Done);
        mRootRef=new Firebase("https://fir-eg-ca49b.firebaseio.com/Users");
        buttonlogout=(Button)findViewById(R.id.logout);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        FirebaseUser user=firebaseAuth.getCurrentUser();

        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            }
        });


        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String username=name.getText().toString();
                int userage= Integer.parseInt(age.getText().toString());
                Firebase child1=mRootRef.child("Username");
                Firebase child2=child1.child("UserAge");
                child1.setValue(username);
                child2.setValue(userage);
            }
        });
    }
}
