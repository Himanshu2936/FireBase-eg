package com.honey.firebaseeg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button buttonRegister;
    EditText editTextEmail, editTextPasword;
    TextView textViewSignin;
    ProgressDialog registerprogress;
    private FirebaseAuth fireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonRegister=(Button)findViewById(R.id.login);
        editTextEmail =(EditText)findViewById(R.id.email);
        editTextPasword =(EditText)findViewById(R.id.password);
        textViewSignin=(TextView)findViewById(R.id.signinclick);
        registerprogress=new ProgressDialog(this);
        fireBaseAuth=FirebaseAuth.getInstance();

        if(fireBaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
        }



        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=editTextEmail.getText().toString().trim();
                password=editTextPasword.getText().toString().trim();
                if(email.isEmpty() || password.isEmpty()){
                    makeToast("Email or password is empty");
                }
                else{
                    registerprogress.setMessage("Registering User...");
                    registerprogress.show();
                    registerUser();
                    }
            }
        });
        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start login activity here
                finish();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
    private  void registerUser(){
       String  email=editTextEmail.getText().toString().trim();
       String password=editTextPasword.getText().toString().trim();
        fireBaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                registerprogress.dismiss();
                makeToast("Registered Succesfully");

                    finish();
                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
                else{
                registerprogress.dismiss();
                makeToast("Something went wrong! Try agian...");}
            }
        });
    }
    void makeToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
