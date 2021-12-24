package com.example.blogpages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogpages.myclass.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginID, etPassword;
    Button btnLogin ;
    ProgressDialog pd  ;
    TextView tvRegister;
    SharedPreferences sharedPreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginID = findViewById(R.id.etLoginID);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        sharedPreferences = getSharedPreferences("loginPreference",0);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLogin login = new CheckLogin();
                login.execute();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Registration.class);
                startActivity(intent);
            }
        });



    }
    public class CheckLogin extends AsyncTask<String,String,String> {
        String login,password;
        ProgressDialog progressDialog ;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            login = etLoginID.getText().toString();
            password = etPassword.getText().toString();
            progressDialog  = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Loading....");
            Log.d("In Pre","In pred");
            progressDialog.show();

        }
        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            Log.d("do","1");
            WebServiceCall call = new WebServiceCall();
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("email",login);
            hashMap.put("password",password);
            try {

                Log.d("do","2");
                result = call.postData(GlobalURL.LOGIN_URL,WebServiceCall.POST,hashMap);

                Log.d("do","3");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.v("Result",result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try{
                JSONObject jsonObject = new JSONObject(s);
                String isValid = jsonObject.getString("msg");
                if(isValid.equals("Valid"))
                {
                    String userid = jsonObject.getString("userid");
                    String username = jsonObject.getString("username");
                    String email = jsonObject.getString("email");
                    Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor  = sharedPreferences.edit();
                    editor.putString("userid",userid);
                    editor.putString("username",username);
                    editor.putString("email",email);
                    editor.commit() ;
                    Toast.makeText(LoginActivity.this, "Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}