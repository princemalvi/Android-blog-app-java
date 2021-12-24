package com.example.blogpages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    EditText etUsername,etLoginID, etPassword,etRepassword;
    Button btnRegister ;
    ProgressDialog pd  ;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etUsername = findViewById(R.id.etUsername);
        etLoginID = findViewById(R.id.etLoginID);
        etPassword = findViewById(R.id.etPassword);
        etRepassword = findViewById(R.id.etRepassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvRegister = findViewById(R.id.tvRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getText().toString().equals(etRepassword.getText().toString())) {
                    Register register = new Register();
                    register.execute();
                }else{
                    Toast.makeText(Registration.this, "Please Enter Password Correct", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public class Register extends AsyncTask<String,String,String> {
        String username,login,password;
        ProgressDialog progressDialog ;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            login = etLoginID.getText().toString();
            password = etPassword.getText().toString();
            username = etUsername.getText().toString();
            progressDialog  = new ProgressDialog(Registration.this);
            progressDialog.setMessage("Loading....");
            progressDialog.show();

        }
        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            WebServiceCall call = new WebServiceCall();
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("username",username);
            hashMap.put("email",login);
            hashMap.put("password",password);
            try {
                result = call.postData(GlobalURL.REGISTER_URL,WebServiceCall.POST,hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("Result",result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try{
                JSONObject jsonObject = new JSONObject(s);
                String isValid = jsonObject.getString("msg");
                if(isValid.equals("Success"))
                {
                    Intent intent = new Intent(Registration.this,LoginActivity.class);
                    startActivity(intent);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}