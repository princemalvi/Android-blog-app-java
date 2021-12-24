package com.example.blogpages;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;


public class AddBlogFragment extends Fragment {

    EditText title,category,desc;
    Button btnPost;
    SharedPreferences sp ;
    String userid ;
    public AddBlogFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_blog, container, false);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.category);
        category = view.findViewById(R.id.postDescription);
        btnPost = view.findViewById(R.id.btnPost);
        sp = getContext().getSharedPreferences("loginPreference",0);
        userid = sp.getString("userid","");

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostBlogTask().execute();
            }
        });
        return view;
    }



    public class PostBlogTask extends AsyncTask<String,String,String> {

        ProgressDialog progressDialog ;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog  = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading....");
            progressDialog.show();

        }
        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            WebServiceCall call = new WebServiceCall();
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("userid", userid);
            hashMap.put("title",title.getText().toString());
            hashMap.put("category",title.getText().toString());
            hashMap.put("desc",desc.getText().toString());
            try {
                result = call.postData(GlobalURL.BLOGPOST_URL,WebServiceCall.POST,hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            title.setText("");
            desc.setText("");
            category.setText("");
            progressDialog.dismiss();

            try{
                JSONObject object = new JSONObject(s);
                String isValid = object.getString("msg");
                if(isValid.equals("Success")) {
                    Toast.makeText(getContext(), "Post Success!!", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}