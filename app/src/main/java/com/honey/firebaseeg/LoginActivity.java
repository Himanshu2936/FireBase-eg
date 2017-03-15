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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button buttonLogin;
    EditText editTextEmail, editTextPasword;
    TextView textViewSignUp;
    ProgressDialog loginDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin=(Button)findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
        }


        editTextEmail =(EditText)findViewById(R.id.email);
        editTextPasword =(EditText)findViewById(R.id.password);
        textViewSignUp=(TextView)findViewById(R.id.signup);
        loginDialog=new ProgressDialog(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            userLogin();
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
    private void userLogin(){
        String email,password;
        email=editTextEmail.getText().toString().trim();
        password=editTextPasword.getText().toString().trim();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Enter correct email or password",Toast.LENGTH_SHORT);
        }
        else{
            loginDialog.setMessage("Loging In ");
            loginDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    loginDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                }
                    else{
                    Toast.makeText(LoginActivity.this,"can't login try later",Toast.LENGTH_SHORT).show();
                }
                }
            });
        }
    }
}
